package com.iac.ambit.utils;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.utils.Messages;
import org.apache.commons.logging.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.iac.ambit.utils.DateUtils;

public class LogHandler extends BasicHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Log log = LogFactory.getLog(LogHandler.class.getName());

	long start = -1;

	private boolean writeToConsole = false;

	private String filename = Config.getProperty("HttpServerAddress");

	public void init() {
		super.init();

		Object opt = this.getOption("LogHandler.writeToConsole");
		if (opt != null && opt instanceof String
				&& "true".equalsIgnoreCase((String) opt))
			writeToConsole = true;

		opt = this.getOption("LogHandler.fileName");
		if (opt != null && opt instanceof String)
			filename = (String) opt;
	}

	public void invoke(MessageContext msgContext) throws AxisFault {
		log.debug("Enter: LogHandler::invoke");
		if (msgContext.getPastPivot() == false) {
			start = System.currentTimeMillis();
		} else {
			logMessages(msgContext);
		}
		log.debug("Exit: LogHandler::invoke");
	}

	private void logMessages(MessageContext msgContext) throws AxisFault {
		try {
			PrintWriter writer = null;

			writer = getWriter();

			Message inMsg = msgContext.getRequestMessage();
			Message outMsg = msgContext.getResponseMessage();

			writer
					.println("=======================================================");
			writer.println("= DateTime: " + DateUtils.getCurLDate() + " "
					+ DateUtils.getCurrentTime());
			if (start != -1) {
				writer.println("= "
						+ Messages.getMessage("elapsed00", ""
								+ (System.currentTimeMillis() - start)));
			}

			writer.println("= "
					+ Messages.getMessage("inMsg00", (inMsg == null ? "null"
							: inMsg.getSOAPPartAsString())));
			writer.println("= "
					+ Messages.getMessage("outMsg00", (outMsg == null ? "null"
							: outMsg.getSOAPPartAsString())));
			writer
					.println("=======================================================");

			if (!writeToConsole) {
				writer.close();
			}

		} catch (Exception e) {
			log.error(Messages.getMessage("exception00"), e);
			throw AxisFault.makeFault(e);
		}
	}

	private PrintWriter getWriter() throws IOException {
		PrintWriter writer;

		if (writeToConsole) {

			writer = new PrintWriter(System.out);
		} else {

			if (filename == null) {
				filename = Config.getProperty("HttpServerAddress");
			}
			writer = new PrintWriter(new FileWriter(filename, true));
		}
		return writer;
	}

	public void onFault(MessageContext msgContext) {
		try {
			logMessages(msgContext);
		} catch (AxisFault axisFault) {
			log.error(Messages.getMessage("exception00"), axisFault);
		}
	}

}
