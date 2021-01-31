package co.aurasphere.reports.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

import co.aurasphere.reports.model.ExpenseAccount;
import co.aurasphere.reports.model.Order;
import co.aurasphere.reports.model.Report;
import co.aurasphere.reports.model.ReportHeader;
import co.aurasphere.reports.model.ReportSummary;
import co.aurasphere.reports.model.Timesheet;
import co.aurasphere.reports.model.TimesheetRow;

/**
 * Dao for {@link Report}.
 * 
 * @author Donato Rimenti
 */
@Repository
public class ReportDao {

	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(ReportDao.class);

	/**
	 * The collection name.
	 */
	private final static String COLLECTION_NAME = "report";

	/**
	 * The user field.
	 */
	private final static String USER_FIELD = Fields.UNDERSCORE_ID + ".user";

	/**
	 * The timesheet field.
	 */
	private static final String TIMESHEET_FIELD = "timesheet";

	/**
	 * The expense account field.
	 */
	private static final String EXPENSE_ACCOUNT_FIELD = "expenseAccount";

	/**
	 * The report date field.
	 */
	private static final String DATE_FIELD = Fields.UNDERSCORE_ID + ".date";

	/**
	 * The creation time field.
	 */
	private static final String CREATION_TIME_FIELD = "creationTime";

	/**
	 * The update time field.
	 */
	private static final String UPDATE_TIME_FIELD = "updateTime";

	/**
	 * The locked field.
	 */
	private static final String LOCK_FIELD = "locked";

	private static final String ROWS_FIELD = TIMESHEET_FIELD + ".rows";

	/**
	 * The mongo template.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Removes all the reports from the collection.
	 */
	public void deleteAll() {
		LOG.warn("ReportDao.deleteAll()");
		mongoTemplate.getCollection(COLLECTION_NAME).drop();
	}

	/**
	 * Adds a {@link Timesheet} to an existing {@link Report} or creates a new
	 * report with the given timesheet.
	 * 
	 * @param user
	 *            the user whose timesheet belongs to
	 * @param timesheet
	 *            the timesheet to add
	 * @param date
	 *            the date of the timesheet
	 */
	public void upsertTimesheet(String user, Timesheet timesheet, YearMonth date) {
		LOG.info("ReportDao.upsertTimesheet({}, {}, {})", user, timesheet, date);

		// Selects the report if present.
		ReportHeader id = new ReportHeader(user, date);
		Query query = new Query(Criteria.where(Fields.UNDERSCORE_ID).is(id).and(LOCK_FIELD).is(false));

		// Adds the timesheet to the current report or a new one.
		Update update = new Update();
		update.set(TIMESHEET_FIELD, timesheet);
		update.set(UPDATE_TIME_FIELD, LocalDateTime.now());
		update.setOnInsert(CREATION_TIME_FIELD, LocalDateTime.now());

		// Executes the operation.
		mongoTemplate.upsert(query, update, COLLECTION_NAME);
	}

	/**
	 * Adds an {@link ExpenseAccount} to an existing {@link Report} or creates a
	 * new report with the given expense account.
	 * 
	 * @param user
	 *            the user whose expense account belongs to
	 * @param expenseAccount
	 *            the expense account to add
	 * @param date
	 *            the date of the expense account
	 */
	public void upsertExpenseAccount(String user, ExpenseAccount expenseAccount, YearMonth date) {
		LOG.info("ReportDao.upsertExpenseAccount({}, {}, {})", user, expenseAccount, date);

		// Selects the report if present.
		ReportHeader id = new ReportHeader(user, date);
		Query query = new Query(Criteria.where(Fields.UNDERSCORE_ID).is(id).and(LOCK_FIELD).is(false));

		// Adds the expense account to the current report or a new one.
		Update update = new Update();
		update.set(EXPENSE_ACCOUNT_FIELD, expenseAccount);
		update.set(UPDATE_TIME_FIELD, LocalDateTime.now());
		update.setOnInsert(CREATION_TIME_FIELD, LocalDateTime.now());
		update.setOnInsert(Fields.UNDERSCORE_ID, id);

		// Executes the operation.
		mongoTemplate.upsert(query, update, COLLECTION_NAME);
	}

	public Report getUserReport(String email, YearMonth date) {
		LOG.trace("ReportDao.getUserReport({}, {})", email, date);

		ReportHeader id = new ReportHeader(email, date);
		return mongoTemplate.findById(id, Report.class);
	}

	public void saveReport(Report report) {
		LOG.trace("ReportDao.saveReport({})", report);
		mongoTemplate.insert(report);
	}

	public List<Report> getReportsSummary(YearMonth date) {
		LOG.trace("ReportDao.getReportsSummary({})", date);
		// TODO: aggregate results instead
		// List<AggregationOperation> aggregationOperations = new
		// ArrayList<AggregationOperation>();
		//
		// // 1. Filter by date.
		// aggregationOperations.add(Aggregation.match(Criteria.where(DATE_FIELD).is(date)));
		//
		// // 2. Unwind rows.
		// aggregationOperations.add(Aggregation.unwind("timesheet.rows"));
		//
		// // 3. Sum hours.
		// aggregationOperations.add(Aggregation.group("timesheet.rows.name").sum("timesheet.rows.hours").as("timesheet.rows.totalHours"));
		//
		// // 4.
		// aggregationOperations.add(Aggregation.group("timesheet.rows.name").sum("timesheet.rows.hours").as("timesheet.rows.totalHours"));

		return mongoTemplate.find(new Query(Criteria.where(DATE_FIELD).is(date)), Report.class);
	}

	public DBObject tmp(YearMonth date) {
		LOG.trace("ReportDao.getReportsSummary({})", date);
		// TODO: aggregate results instead
		List<AggregationOperation> aggregationOperations = new ArrayList<AggregationOperation>();

		// 1. Filter by date.
		aggregationOperations.add(Aggregation.match(Criteria.where(DATE_FIELD).is(date)));

		// aggregationOperations.add(Aggregation.project().andExclude(Fields.UNDERSCORE_ID));

		// 2. Unwind rows.
		aggregationOperations.add(Aggregation.unwind("timesheet.rows"));

		// aggregationOperations.add(Aggregation.match(Criteria.where("timesheet.rows.name").is("Ferie")));
		//
		// aggregationOperations.add(Aggregation.project().and(AccumulatorOperators.Sum.sumOf("timesheet.rows.hours")));

		// 3. Sum hours.
		aggregationOperations.add(Aggregation.project("timesheet.rows.order", "timesheet.rows.name").and(AccumulatorOperators.Sum.sumOf("timesheet.rows.hours")).as("totalHours"));
		
		// 4. Group back by ID.
		aggregationOperations.add(Aggregation.group().first(Aggregation.previousOperation()).as("_id"));

		
//		aggregationOperations.add(Aggregation
//				.facet(Aggregation.match(Criteria.where("timesheet.rows.name").is("Ferie")), Aggregation.project()
//						.and(AccumulatorOperators.Sum.sumOf("timesheet.rows.hours")).as("vacationHours"))
//				.as("summaryHours"));

//		aggregationOperations.add(Aggregation.unwind("summaryHours"));
		// aggregationOperations.add(Aggregation
		// .project(SetOperators.SetUnion.arrayAsSet());

		// aggregationOperations.add(Aggregation.group("timesheet.rows.name").sum("timesheet.rows.hours").as("timesheet.rows.totalHours"));

		// 4.
		// aggregationOperations.add(Aggregation.group("timesheet.rows.name").sum("timesheet.rows.hours").as("timesheet.rows.totalHours"));

		// return mongoTemplate.find(new
		// Query(Criteria.where(DATE_FIELD).is(date)), Report.class);

		return mongoTemplate
				.aggregate(Aggregation.newAggregation(Report.class, aggregationOperations), ReportSummary.class)
				.getRawResults();
	}

	public void updateReportsLock(List<Report> reports) {
		LOG.trace("ReportDao.updateReportsLock(*)");
		List<Pair<Query, Update>> bulkUpdate = new ArrayList<Pair<Query, Update>>();

		for (Report r : reports) {
			Query query = new Query(Criteria.where(Fields.UNDERSCORE_ID).is(r.getHeader()));
			Update update = new Update();
			update.set(LOCK_FIELD, r.isLocked());

			Pair<Query, Update> pair = Pair.of(query, update);
			bulkUpdate.add(pair);
		}

		mongoTemplate.bulkOps(BulkMode.UNORDERED, Report.class).updateMulti(bulkUpdate).execute();
	}

	public void updateOldReportsWithNewRows(String email, List<Order> userOrders) {
		LOG.trace("ReportDao.updateOldReportsWithNewRows({}, *)", email);
		List<Pair<Query, Update>> bulkUpdate = new ArrayList<Pair<Query, Update>>();

		for (Order order : userOrders) {
			Criteria criteria = Criteria.where(USER_FIELD).is(email)
					.and(ROWS_FIELD + ".order.customer")
					.nin(order.getCustomer())
					.and(DATE_FIELD).gte(YearMonth.from(order.getFrom()));
			
			LocalDate to = order.getTo();
			if (to != null) {
				criteria = criteria.lte(YearMonth.from(order.getTo()));
			}
			
			Query query = new Query(criteria);

			Update update = new Update();
			update.push(ROWS_FIELD).atPosition(0).each(new TimesheetRow("Lavorate", order),
					new TimesheetRow("Straordinari", order));

			Pair<Query, Update> pair = Pair.of(query, update);
			bulkUpdate.add(pair);
		}

		mongoTemplate.bulkOps(BulkMode.UNORDERED, Report.class).updateMulti(bulkUpdate).execute();
	}
	
}