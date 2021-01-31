package co.aurasphere.reports.utilities;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Task for sending an email asynchronously.
 * 
 * @author Donato Rimenti
 * 
 */
public class SendMailTask implements Runnable {

	/**
	 * The mail sender.
	 */
	private JavaMailSender sender;

	/**
	 * The mail preparator.
	 */
	private MimeMessagePreparator preparator;

	/**
	 * Instantiates a new SendMailTask.
	 *
	 * @param sender
	 *            the {@link #sender}
	 * @param preparator
	 *            the {@link #preparator}
	 */
	public SendMailTask(JavaMailSender sender, MimeMessagePreparator preparator) {
		this.sender = sender;
		this.preparator = preparator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.sender.send(preparator);
	}

}
