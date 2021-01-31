package co.aurasphere.reports.utilities;

import java.time.YearMonth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

@Component
public class YearMonthDbObjectConverter implements Converter<BasicDBObject, YearMonth> {

	@Override
	public YearMonth convert(BasicDBObject source) {
		return YearMonth.of(source.getInt("year"), source.getInt("month"));
	}

}