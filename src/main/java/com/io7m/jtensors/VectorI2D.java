/*
 * Copyright © 2012 http://io7m.com
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jtensors;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.io7m.jaux.ApproximatelyEqualDouble;

/**
 * A two-dimensional immutable vector type with double precision elements.
 * 
 * Values of this type are immutable and can therefore be safely accessed from
 * multiple threads.
 */

@Immutable public final class VectorI2D implements VectorReadable2D
{
  /**
   * Calculate the element-wise sum of the vectors <code>v0</code> and the
   * element-wise product of <code>v1</code> and <code>r</code>.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * @param r
   *          The scaling value
   * 
   * @return <code>(v0.x + (v1.x * r), v0.y + (v1.y * r))</code>
   */

  public static @Nonnull VectorI2D addScaled(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1,
    final double r)
  {
    return VectorI2D.add(v0, VectorI2D.scale(v1, r));
  }

  /**
   * Calculate the angle between the vectors <code>v0</code> and
   * <code>v1</code> in radians.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return The angle between the two vectors, in radians
   */

  public static double angle(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    final double m0 = VectorI2D.magnitude(v0);
    final double m1 = VectorI2D.magnitude(v1);
    return Math.acos(VectorI2D.dotProduct(v0, v1) / (m0 * m1));
  }

  /**
   * Calculate the angle between the vectors <code>v0</code> and
   * <code>v1</code> in degrees.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return The angle between the two vectors, in degrees
   */

  public static double angleDegrees(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    return Math.toDegrees(VectorI2D.angle(v0, v1));
  }

  /**
   * Determine whether or not the elements of the two vectors <code>v0</code>
   * and <code>v1</code> are approximately equal.
   * 
   * @see ApproximatelyEqualDouble
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return true, iff <code>v0</code> is approximately equal to
   *         <code>v1</code> , within an appropriate degree of error for
   *         double precision floating point values
   */

  public static boolean approximatelyEqual(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    final boolean ex =
      ApproximatelyEqualDouble.approximatelyEqual(v0.x, v1.x);
    final boolean ey =
      ApproximatelyEqualDouble.approximatelyEqual(v0.y, v1.y);
    return ex && ey;
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the range
   * <code>[minimum .. maximum]</code> inclusive.
   * 
   * @param v
   *          The input vector
   * @param minimum
   *          The minimum allowed value
   * @param maximum
   *          The maximum allowed value
   * 
   * @return A vector with both elements equal to at most <code>maximum</code>
   *         and at least <code>minimum</code>
   */

  public static @Nonnull VectorI2D clamp(
    final @Nonnull VectorI2D v,
    final double minimum,
    final double maximum)
  {
    final double x = Math.min(Math.max(v.x, minimum), maximum);
    final double y = Math.min(Math.max(v.y, minimum), maximum);
    return new VectorI2D(x, y);
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the inclusive range
   * given by the corresponding elements in <code>minimum</code> and
   * <code>maximum</code>.
   * 
   * @param v
   *          The input vector
   * @param minimum
   *          The vector containing the minimum acceptable values
   * @param maximum
   *          The vector containing the maximum acceptable values
   * 
   * @return <code>(min(max(v.x, minimum.x), maximum.x), min(max(v.y, minimum.y), maximum.y))</code>
   */

  public static @Nonnull VectorI2D clampByVector(
    final @Nonnull VectorI2D v,
    final @Nonnull VectorI2D minimum,
    final @Nonnull VectorI2D maximum)
  {
    final double x = Math.min(Math.max(v.x, minimum.x), maximum.x);
    final double y = Math.min(Math.max(v.y, minimum.y), maximum.y);
    return new VectorI2D(x, y);
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the range
   * <code>[-Infinity .. maximum]</code> inclusive.
   * 
   * @param v
   *          The input vector
   * @param maximum
   *          The maximum allowed value
   * 
   * @return A vector with both elements equal to at most <code>maximum</code>
   */

  public static @Nonnull VectorI2D clampMaximum(
    final @Nonnull VectorI2D v,
    final double maximum)
  {
    final double x = Math.min(v.x, maximum);
    final double y = Math.min(v.y, maximum);
    return new VectorI2D(x, y);
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the inclusive range
   * given by the corresponding elements in <code>maximum</code>.
   * 
   * @param v
   *          The input vector
   * @param maximum
   *          The vector containing the maximum acceptable values
   * 
   * @return <code>(min(v.x, maximum.x), min(v.y, maximum.y))</code>
   */

  public static @Nonnull VectorI2D clampMaximumByVector(
    final @Nonnull VectorI2D v,
    final @Nonnull VectorI2D maximum)
  {
    final double x = Math.min(v.x, maximum.x);
    final double y = Math.min(v.y, maximum.y);
    return new VectorI2D(x, y);
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the range
   * <code>[minimum .. Infinity]</code> inclusive.
   * 
   * @param v
   *          The input vector
   * @param minimum
   *          The minimum allowed value
   * 
   * @return A vector with both elements equal to at least
   *         <code>minimum</code>
   */

  public static @Nonnull VectorI2D clampMinimum(
    final @Nonnull VectorI2D v,
    final double minimum)
  {
    final double x = Math.max(v.x, minimum);
    final double y = Math.max(v.y, minimum);
    return new VectorI2D(x, y);
  }

  /**
   * Clamp the elements of the vector <code>v</code> to the inclusive range
   * given by the corresponding elements in <code>minimum</code>.
   * 
   * @param v
   *          The input vector
   * @param minimum
   *          The vector containing the minimum acceptable values
   * 
   * @return <code>(max(v.x, minimum.x), max(v.y, minimum.y))</code>
   */

  public static @Nonnull VectorI2D clampMinimumByVector(
    final @Nonnull VectorI2D v,
    final @Nonnull VectorI2D minimum)
  {
    final double x = Math.max(v.x, minimum.x);
    final double y = Math.max(v.y, minimum.y);
    return new VectorI2D(x, y);
  }

  /**
   * Calculate the distance between the two vectors <code>v0</code> and
   * <code>v1</code>.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return The distance between the two vectors
   */

  public static double distance(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    return VectorI2D.magnitude(VectorI2D.subtract(v0, v1));
  }

  /**
   * Calculate the scalar product of the vectors <code>v0</code> and
   * <code>v1</code>.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return The scalar product of the two vectors
   */

  public static double dotProduct(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    final double x = v0.x * v1.x;
    final double y = v0.y * v1.y;
    return x + y;
  }

  /**
   * Linearly interpolate between <code>v0</code> and <code>v1</code> by the
   * amount <code>alpha</code>.
   * 
   * The <code>alpha</code> parameter controls the degree of interpolation,
   * such that:
   * 
   * <ul>
   * <li><code>interpolateLinear(v0, v1, 0.0) = v0</code></li>
   * <li><code>interpolateLinear(v0, v1, 1.0) = v1</code></li>
   * </ul>
   * 
   * @param v0
   *          The left input vector.
   * @param v1
   *          The right input vector.
   * @param alpha
   *          The interpolation value, between <code>0.0</code> and
   *          <code>1.0</code>.
   * 
   * @return <code>(1 - alpha) * v0 + alpha * v1</code>
   */

  public static @Nonnull VectorI2D interpolateLinear(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1,
    final double alpha)
  {
    final @Nonnull VectorI2D w0 = VectorI2D.scale(v0, 1.0 - alpha);
    final @Nonnull VectorI2D w1 = VectorI2D.scale(v1, alpha);
    return VectorI2D.add(w0, w1);
  }

  /**
   * Calculate the magnitude of the vector <code>v</code>.
   * 
   * Correspondingly, <code>magnitude(normalize(v)) == 1.0</code>.
   * 
   * @param v
   *          The input vector
   * 
   * @return The magnitude of the input vector
   */

  public static double magnitude(
    final @Nonnull VectorI2D v)
  {
    return Math.sqrt(VectorI2D.magnitudeSquared(v));
  }

  /**
   * Calculate the squared magnitude of the vector <code>v</code>.
   * 
   * @param v
   *          The input vector
   * @return The squared magnitude of the input vector
   */

  public static double magnitudeSquared(
    final @Nonnull VectorI2D v)
  {
    return VectorI2D.dotProduct(v, v);
  }

  /**
   * Normalize the vector <code>v</code>, preserving its direction but
   * reducing it to unit length.
   * 
   * @param v
   *          The input vector
   * 
   * @return A vector with the same orientation as <code>v</code> but with
   *         magnitude equal to <code>1.0</code>
   */

  public static @Nonnull VectorI2D normalize(
    final @Nonnull VectorI2D v)
  {
    final double m = VectorI2D.magnitudeSquared(v);
    if (m > 0) {
      final double reciprocal = 1.0 / Math.sqrt(m);
      return VectorI2D.scale(v, reciprocal);
    }
    return v;
  }

  /**
   * Calculate the projection of the vector <code>p</code> onto the vector
   * <code>q</code>.
   * 
   * @return <code>((dotProduct p q) / magnitudeSquared q) * q</code>
   */

  public static @Nonnull VectorI2D projection(
    final @Nonnull VectorI2D p,
    final @Nonnull VectorI2D q)
  {
    final double dot = VectorI2D.dotProduct(p, q);
    final double qms = VectorI2D.magnitudeSquared(q);
    final double s = dot / qms;
    return VectorI2D.scale(p, s);
  }

  /**
   * Scale the vector <code>v</code> by the scalar <code>r</code>.
   * 
   * @param v
   *          The input vector
   * @param r
   *          The scaling value
   * 
   * @return <code>(v.x * r, v.y * r)</code>
   */

  public static @Nonnull VectorI2D scale(
    final @Nonnull VectorI2D v,
    final double r)
  {
    return new VectorI2D(v.x * r, v.y * r);
  }

  /**
   * Subtract the vector <code>v0</code> from the vector <code>v1</code>.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return <code>(v0.x - v1.x, v0.y - v1.y)</code>
   */

  public static @Nonnull VectorI2D subtract(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    return new VectorI2D(v0.x - v1.x, v0.y - v1.y);
  }

  public final double           x;
  public final double           y;

  /**
   * The zero vector.
   */

  public static final VectorI2D ZERO = new VectorI2D(0.0, 0.0);

  /**
   * Calculate the absolute value of the vector <code>v</code>.
   * 
   * @param v
   *          The input vector
   * 
   * @return <code>(abs v.x, abs v.y)</code>
   */

  public static @Nonnull VectorI2D absolute(
    final @Nonnull VectorI2D v)
  {
    return new VectorI2D(Math.abs(v.x), Math.abs(v.y));
  }

  /**
   * Calculate the element-wise sum of the vectors <code>v0</code> and
   * <code>v1</code>.
   * 
   * @param v0
   *          The left input vector
   * @param v1
   *          The right input vector
   * 
   * @return <code>(v0.x + v1.x, v0.y + v1.y)</code>
   */

  public static @Nonnull VectorI2D add(
    final @Nonnull VectorI2D v0,
    final @Nonnull VectorI2D v1)
  {
    return new VectorI2D(v0.x + v1.x, v0.y + v1.y);
  }

  /**
   * Default constructor, initializing the vector with values
   * <code>[0.0, 0.0]</code>.
   */

  public VectorI2D()
  {
    this.x = 0.0;
    this.y = 0.0;
  }

  /**
   * Construct a vector initialized with the given values.
   */

  public VectorI2D(
    final double x,
    final double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Construct a vector initialized with the values given in the vector
   * <code>v</code>.
   */

  public VectorI2D(
    final VectorReadable2D v)
  {
    this.x = v.getXD();
    this.y = v.getYD();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override public boolean equals(
    final Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final VectorI2D other = (VectorI2D) obj;
    if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
      return false;
    }
    if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
      return false;
    }
    return true;
  }

  @Override public double getXD()
  {
    return this.x;
  }

  @Override public double getYD()
  {
    return this.y;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(this.x);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(this.y);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[VectorI2D ");
    builder.append(this.x);
    builder.append(" ");
    builder.append(this.y);
    builder.append("]");
    return builder.toString();
  }
}