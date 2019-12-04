package com.topcoder.commons.utils;
import java.lang.*;
import org.apache.log4j.*;
import java.util.*;
//import java.util.logging.Logger;

/**
 * This is a utility class that provides static methods for logging method entrance, method exit and exception using Log4j library. It allows to optionally log method input/output parameters and method execution time. Exceptions are logged together with stack traces. Default priority for method entrance/exit message is DEBUG, for exceptions - ERROR; but both can be overridden.
 *
 * Thread Safety:
 * This class is immutable and thread safe when array parameters passed to it are used by the caller in thread safe manner.
*/
public class Log4jUtility {
/**
 * Empty private constructor.
 *
 * Implementation Notes:
 * Do nothing.
*/
public Log4jUtility() {
}
/**
 * Logs the method entrance together with input parameters (if present). It's assumed that paramNames and paramValues contain the same number of elements.
 * When this method is used, DEBUG priority is used.
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName)
 * paramNames - the names of input parameters (null of method doesn't accept any parameters)
 * paramValues - the values of input parameters
 *
 * Implementation Notes:
 * 1. logEntrance(logger, signature, paramNames, paramValues, Priority.DEBUG);
 * @param paramValues the values of input parameters
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName)
 * @param paramNames the names of input parameters (null of method doesn't accept any parameters)
 * @param logger the logger to be used (null if logging is not required to be performed)
*/
public static void logEntrance(Logger logger, String signature, String[] paramNames, Object[] paramValues) {
    logEntrance(logger, signature, paramNames, paramValues, Priority.DEBUG);
}
/**
 * Logs the method entrance together with input parameters (if present) and timestamp (optionally). It's assumed that paramNames and paramValues contain the same number of elements.
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName)
 * paramNames - the names of input parameters (null of method doesn't accept any parameters)
 * paramValues - the values of input parameters
 * priority - the logging priority to be used
 *
 * Implementation Notes:
 * 1. If logger == null then return;
 * 2. logger.log(priority, LoggingUtilityHelper.getMethodEntranceMessage(signature));
 * 3. If paramNames != null then
 *      3.1. logger.log(priority, LoggingUtilityHelper.getInputParametersMessage(paramNames, paramValues));
 * @param paramValues the values of input parameters
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName)
 * @param paramNames the names of input parameters (null of method doesn't accept any parameters)
 * @param logger the logger to be used (null if logging is not required to be performed)
 * @param priority the logging priority to be used
*/
public static void logEntrance(Logger logger, String signature, String[] paramNames, Object[] paramValues, Priority priority) {
    if (logger == null) {
        return;
    }

    logger.log(priority, LoggingUtilityHelper.getMethodEntranceMessage(signature));
    if (paramNames != null) {
        logger.log(priority, LoggingUtilityHelper.getInputParametersMessage(paramNames, paramValues));
    }
}
/**
 * Logs the method exit together with the returned value (if present).
 * When this method is used, method execution time is not logged and DEBUG priority is used.
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName)
 * value - the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 *
 * Implementation Notes:
 * 1. logExit(logger, signature, value, null);
 * @param value the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName)
 * @param logger the logger to be used (null if logging is not required to be performed)
*/
public static void logExit(Logger logger, String signature, Object[] value) {
    logExit(logger, signature, value, null);
}
/**
 * Logs the method exit together with the returned value (if present) and method execution time.
 * When this method is used, DEBUG priority is used.
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName)
 * value - the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 * entranceTimestamp - the method entrance timestamp (null if not available), is used for calculating method execution time
 *
 * Implementation Notes:
 * 1. logExit(logger, signature, value, entranceTimestamp, Priority.DEBUG);
 * @param value the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName)
 * @param entranceTimestamp the method entrance timestamp (null if not available), is used for calculating method execution time
 * @param logger the logger to be used (null if logging is not required to be performed)
*/
public static void logExit(Logger logger, String signature, Object[] value, Date entranceTimestamp) {
    logExit(logger, signature, value, entranceTimestamp, Priority.DEBUG);
}
/**
 * Logs the method exit together with the returned value (if present) and method execution time.
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName)
 * value - the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 * entranceTimestamp - the method entrance timestamp (null if not available), is used for calculating method execution time
 * priority - the logging priority to be used
 *
 * Implementation Notes:
 * 1. If logger == null then return;
 * 2. logger.log(priority, LoggingUtilityHelper.getMethodExitMessage(signature, entranceTimestamp));
 * 3. If value != null then
 *      3.1. logger.log(priority, LoggingUtilityHelper.getOutputValueMessage(value[0]));
 * @param value the value returned from the method (should contain 1 element with the returned value, or should be null if the method returns void)
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName)
 * @param entranceTimestamp the method entrance timestamp (null if not available), is used for calculating method execution time
 * @param logger the logger to be used (null if logging is not required to be performed)
 * @param priority the logging priority to be used
*/
public static void logExit(Logger logger, String signature, Object[] value, Date entranceTimestamp, Priority priority) {
    if (logger == null) {
        return;
    }
    logger.log(priority, LoggingUtilityHelper.getMethodExitMessage(signature, entranceTimestamp));
    if (value != null) {
        logger.log(priority, LoggingUtilityHelper.getOutputValueMessage(value[0]));
    }
}
/**
 * Logs the given exception.
 * When this method is used, ERROR priority is used.
 *
 * Generic Parameters:
 * T - the type of the exception to be logged and returned
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName) where the exception is logged
 * exception - the exception to be logged (assumed to be not null)
 *
 * Returns:
 * the logged exception
 *
 * Implementation Notes:
 * 1. Return logException(logger, signature, exception, Priority.ERROR).
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName) where the exception is logged
 * @param exception the exception to be logged (assumed to be not null)
 * @param logger the logger to be used (null if logging is not required to be performed)
 * @return the logged exception
*/
public static <T extends Throwable>T logException(Logger logger, String signature, T exception) {
    return logException(logger, signature, exception, Priority.ERROR);
}
/**
 * Logs the given exception using the specified priority.
 *
 * Generic Parameters:
 * T - the type of the exception to be logged and returned
 *
 * Parameters:
 * logger - the logger to be used (null if logging is not required to be performed)
 * signature - the signature that uniquely identifies the method (e.g. className#methodName) where the exception is logged
 * exception - the exception to be logged (assumed to be not null)
 * priority - the logging priority to be used
 *
 * Returns:
 * the logged exception
 *
 * Implementation Notes:
 * 1. If logger == null then return;
 * 2. logger.log(priority, LoggingUtilityHelper.getExceptionMessage(signature, exception));
 * 1. Return exception.
 * @param signature the signature that uniquely identifies the method (e.g. className#methodName) where the exception is logged
 * @param exception the exception to be logged (assumed to be not null)
 * @param logger the logger to be used (null if logging is not required to be performed)
 * @param priority the logging priority to be used
 * @return the logged exception
*/
public static <T extends Throwable>T logException(Logger logger, String signature, T exception, Priority priority) {
    if (logger == null) {
        return null;
    }
    logger.log(priority, LoggingUtilityHelper.getExceptionMessage(signature, exception));
    return exception;
}
}

