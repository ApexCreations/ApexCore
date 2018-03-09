package io.apexcreations.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Utils {

  private static DecimalFormat decimalFormat;

  private static DecimalFormat getDecimalFormat() {
    if (decimalFormat == null) {
      decimalFormat = new DecimalFormat();
      decimalFormat.setMaximumFractionDigits(2);
      decimalFormat.setMinimumFractionDigits(0);
      decimalFormat.setGroupingUsed(false);
    }
    return decimalFormat;
  }

  public static boolean isDouble(String s) {
    try {
      Double.parseDouble(s);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  public static boolean isInteger(String s) {
    return isInteger(s, 10);
  }

  public static boolean isInteger(String s, int radix) {
    if (s.isEmpty()) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 && s.charAt(i) == '-') {
        if (s.length() == 1) {
          return false;
        } else {
          continue;
        }
      }
      if (Character.digit(s.charAt(i), radix) < 0) {
        return false;
      }
    }
    return true;
  }

  public static String toString(Location location) {
    return (location.getWorld().getName() + ":" + location.getX()
        + ":" + location.getY() + ":" + location.getZ() + ":"
        + location.getYaw() + ":" + location.getPitch());
  }

  public static String formatCurrency(BigDecimal bigDecimal) {
    return getDecimalFormat().format(bigDecimal);
  }

  public static Location fromString(String string) {
    String[] split = string.split(":");
    return new Location(
        Bukkit.getWorld(split[0]),
        Double.parseDouble(split[1]),
        Double.parseDouble(split[2]),
        Double.parseDouble(split[3]),
        Float.parseFloat(split[4]),
        Float.parseFloat(split[5]));
  }
}
