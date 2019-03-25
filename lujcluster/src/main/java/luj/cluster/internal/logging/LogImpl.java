package luj.cluster.internal.logging;

import akka.event.LoggingAdapter;
import luj.cluster.api.logging.Log;
import org.slf4j.Marker;

final class LogImpl implements Log {

  LogImpl(LoggingAdapter impl) {
    _impl = impl;
  }

  @Override
  public void error(String message) {
    _impl.error(message);
  }

  @Override
  public void error(String template, Object arg1) {
    _impl.error(template, arg1);
  }

  @Override
  public void error(String template, Object arg1, Object arg2) {
    _impl.error(template, arg1, arg2);
  }

  @Override
  public void error(String format, Object... arguments) {

  }

  @Override
  public void error(String msg, Throwable t) {

  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return false;
  }

  @Override
  public void error(Marker marker, String msg) {

  }

  @Override
  public void error(Marker marker, String format, Object arg) {

  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {

  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {

  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {

  }

  public void error(String template, Object arg1, Object arg2, Object arg3) {
    _impl.error(template, arg1, arg2, arg3);
  }

  public void error(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
    _impl.error(template, arg1, arg2, arg3, arg4);
  }

  public void warning(String message) {
    _impl.warning(message);
  }

  public void warning(String template, Object arg1) {
    _impl.warning(template, arg1);
  }

  public void warning(String template, Object arg1, Object arg2) {
    _impl.warning(template, arg1, arg2);
  }

  public void warning(String template, Object arg1, Object arg2, Object arg3) {
    _impl.warning(template, arg1, arg2, arg3);
  }

  public void warning(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
    _impl.warning(template, arg1, arg2, arg3, arg4);
  }

  @Override
  public void info(String message) {
    _impl.info(message);
  }

  @Override
  public void info(String template, Object arg1) {
    _impl.info(template, arg1);
  }

  @Override
  public void info(String template, Object arg1, Object arg2) {
    _impl.info(template, arg1, arg2);
  }

  @Override
  public void info(String format, Object... arguments) {

  }

  @Override
  public void info(String msg, Throwable t) {

  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return false;
  }

  @Override
  public void info(Marker marker, String msg) {

  }

  @Override
  public void info(Marker marker, String format, Object arg) {

  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {

  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {

  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {

  }

  @Override
  public boolean isWarnEnabled() {
    return false;
  }

  @Override
  public void warn(String msg) {

  }

  @Override
  public void warn(String format, Object arg) {

  }

  @Override
  public void warn(String format, Object... arguments) {

  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {

  }

  @Override
  public void warn(String msg, Throwable t) {

  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return false;
  }

  @Override
  public void warn(Marker marker, String msg) {

  }

  @Override
  public void warn(Marker marker, String format, Object arg) {

  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {

  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {

  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {

  }

  @Override
  public boolean isErrorEnabled() {
    return false;
  }

  public void info(String template, Object arg1, Object arg2, Object arg3) {
    _impl.info(template, arg1, arg2, arg3);
  }

  public void info(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
    _impl.info(template, arg1, arg2, arg3, arg4);
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean isTraceEnabled() {
    return false;
  }

  @Override
  public void trace(String msg) {

  }

  @Override
  public void trace(String format, Object arg) {

  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {

  }

  @Override
  public void trace(String format, Object... arguments) {

  }

  @Override
  public void trace(String msg, Throwable t) {

  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return false;
  }

  @Override
  public void trace(Marker marker, String msg) {

  }

  @Override
  public void trace(Marker marker, String format, Object arg) {

  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {

  }

  @Override
  public void trace(Marker marker, String format, Object... argArray) {

  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {

  }

  @Override
  public boolean isDebugEnabled() {
    return false;
  }

  @Override
  public void debug(String message) {
    _impl.debug(message);
  }

  @Override
  public void debug(String template, Object arg1) {
    _impl.debug(template, arg1);
  }

  @Override
  public void debug(String template, Object arg1, Object arg2) {
    _impl.debug(template, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... arguments) {

  }

  @Override
  public void debug(String msg, Throwable t) {

  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return false;
  }

  @Override
  public void debug(Marker marker, String msg) {

  }

  @Override
  public void debug(Marker marker, String format, Object arg) {

  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {

  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {

  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {

  }

  @Override
  public boolean isInfoEnabled() {
    return false;
  }

  public void debug(String template, Object arg1, Object arg2, Object arg3) {
    _impl.debug(template, arg1, arg2, arg3);
  }

  public void debug(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
    _impl.debug(template, arg1, arg2, arg3, arg4);
  }

  private final LoggingAdapter _impl;
}
