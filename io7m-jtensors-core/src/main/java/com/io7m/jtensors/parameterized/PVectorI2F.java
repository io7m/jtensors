/*
 * Copyright © 2015 <code@io7m.com> http://io7m.com
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

package com.io7m.jtensors.parameterized;

import com.io7m.jequality.AlmostEqualFloat;
import com.io7m.jequality.AlmostEqualFloat.ContextRelative;
import com.io7m.jfunctional.Pair;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;
import net.jcip.annotations.Immutable;

/**
 * <p> A two-dimensional immutable vector type with {@code float} elements. </p>
 * <p> Values of this type are immutable and can therefore be safely accessed
 * from multiple threads. </p>
 *
 * @param <T> A phantom type parameter.
 */

@Immutable public final class PVectorI2F<T> implements PVectorReadable2FType<T>
{

  private static final PVectorI2F<?> ZERO = new PVectorI2F<Float>(0.0f, 0.0f);
  private final float x;
  private final float y;

  /**
   * Default constructor, initializing the vector with values {@code [0.0,
   * 0.0]}.
   */

  public PVectorI2F()
  {
    this.x = 0.0f;
    this.y = 0.0f;
  }

  /**
   * Construct a vector initialized with the given values.
   *
   * @param in_x The {@code x} value
   * @param in_y The {@code y} value
   */

  public PVectorI2F(
    final float in_x,
    final float in_y)
  {
    this.x = in_x;
    this.y = in_y;
  }

  /**
   * Construct a vector initialized with the values given in the vector {@code
   * v}.
   *
   * @param in_v The input vector
   */

  public PVectorI2F(
    final PVectorReadable2FType<T> in_v)
  {
    this.x = in_v.getXF();
    this.y = in_v.getYF();
  }

  /**
   * Calculate the absolute value of the vector {@code v}.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter.
   *
   * @return {@code (abs v.x, abs v.y)}
   */

  public static <T> PVectorI2F<T> absolute(
    final PVectorReadable2FType<T> v)
  {
    return new PVectorI2F<T>(Math.abs(v.getXF()), Math.abs(v.getYF()));
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and {@code v1}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter.
   *
   * @return {@code (v0.x + v1.x, v0.y + v1.y)}
   */

  public static <T> PVectorI2F<T> add(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    return new PVectorI2F<T>(v0.getXF() + v1.getXF(), v0.getYF() + v1.getYF());
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and the
   * element-wise product of {@code v1} and {@code r}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param r   The scaling value
   * @param <T> A phantom type parameter.
   *
   * @return {@code (v0.x + (v1.x * r), v0.y + (v1.y * r))}
   */

  public static <T> PVectorI2F<T> addScaled(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1,
    final double r)
  {
    return PVectorI2F.add(v0, PVectorI2F.scale(v1, r));
  }

  /**
   * Determine whether or not the vectors {@code qa} and {@code qb} are equal to
   * within the degree of error given in {@code context}.
   *
   * @param context The equality context
   * @param qa      The left input vector
   * @param qb      The right input vector
   * @param <T>     A phantom type parameter.
   *
   * @return {@code true} iff the vectors are almost equal.
   *
   * @see AlmostEqualFloat#almostEqual(ContextRelative, float, float)
   * @since 7.0.0
   */

  public static <T> boolean almostEqual(
    final ContextRelative context,
    final PVectorReadable2FType<T> qa,
    final PVectorReadable2FType<T> qb)
  {
    final boolean xs =
      AlmostEqualFloat.almostEqual(context, qa.getXF(), qb.getXF());
    final boolean ys =
      AlmostEqualFloat.almostEqual(context, qa.getYF(), qb.getYF());
    return xs && ys;
  }

  /**
   * Calculate the angle between the vectors {@code v0} and {@code v1} in
   * radians.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter.
   *
   * @return The angle between the two vectors, in radians.
   */

  public static <T> double angle(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    final double m0 = PVectorI2F.magnitude(v0);
    final double m1 = PVectorI2F.magnitude(v1);
    final double dp =
      Math.min(Math.max(-1.0, PVectorI2F.dotProduct(v0, v1)), 1.0);
    final double f = m0 * m1;
    final double r = dp / f;
    return Math.acos(r);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * maximum]} inclusive.
   *
   * @param v       The input vector
   * @param minimum The minimum allowed value
   * @param maximum The maximum allowed value
   * @param <T>     A phantom type parameter.
   *
   * @return A vector with both elements equal to at most {@code maximum} and at
   * least {@code minimum}.
   */

  public static <T> PVectorI2F<T> clamp(
    final PVectorReadable2FType<T> v,
    final float minimum,
    final float maximum)
  {
    final float x = Math.min(Math.max(v.getXF(), minimum), maximum);
    final float y = Math.min(Math.max(v.getYF(), minimum), maximum);
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum} and {@code maximum}.
   *
   * @param v       The input vector
   * @param minimum The vector containing the minimum acceptable values
   * @param maximum The vector containing the maximum acceptable values
   * @param <T>     A phantom type parameter.
   *
   * @return {@code (min(max(v.x, minimum.x), maximum.x), min(max(v.y,
   * minimum.y), maximum.y))}
   */

  public static <T> PVectorI2F<T> clampByPVector(
    final PVectorReadable2FType<T> v,
    final PVectorReadable2FType<T> minimum,
    final PVectorReadable2FType<T> maximum)
  {
    final float x =
      Math.min(Math.max(v.getXF(), minimum.getXF()), maximum.getXF());
    final float y =
      Math.min(Math.max(v.getYF(), minimum.getYF()), maximum.getYF());
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [-Infinity
   * .. maximum]} inclusive.
   *
   * @param v       The input vector
   * @param maximum The maximum allowed value
   * @param <T>     A phantom type parameter.
   *
   * @return A vector with both elements equal to at most {@code maximum}
   */

  public static <T> PVectorI2F<T> clampMaximum(
    final PVectorReadable2FType<T> v,
    final float maximum)
  {
    final float x = Math.min(v.getXF(), maximum);
    final float y = Math.min(v.getYF(), maximum);
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code maximum}.
   *
   * @param v       The input vector
   * @param maximum The vector containing the maximum acceptable values
   * @param <T>     A phantom type parameter.
   *
   * @return {@code (min(v.x, maximum.x), min(v.y, maximum.y))}
   */

  public static <T> PVectorI2F<T> clampMaximumByPVector(
    final PVectorReadable2FType<T> v,
    final PVectorReadable2FType<T> maximum)
  {
    final float x = Math.min(v.getXF(), maximum.getXF());
    final float y = Math.min(v.getYF(), maximum.getYF());
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * Infinity]} inclusive.
   *
   * @param v       The input vector
   * @param minimum The minimum allowed value
   * @param <T>     A phantom type parameter.
   *
   * @return A vector with both elements equal to at least {@code minimum}
   */

  public static <T> PVectorI2F<T> clampMinimum(
    final PVectorReadable2FType<T> v,
    final float minimum)
  {
    final float x = Math.max(v.getXF(), minimum);
    final float y = Math.max(v.getYF(), minimum);
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum}.
   *
   * @param v       The input vector
   * @param minimum The vector containing the minimum acceptable values
   * @param <T>     A phantom type parameter.
   *
   * @return {@code (max(v.x, minimum.x), max(v.y, minimum.y))}
   */

  public static <T> PVectorI2F<T> clampMinimumByPVector(
    final PVectorReadable2FType<T> v,
    final PVectorReadable2FType<T> minimum)
  {
    final float x = Math.max(v.getXF(), minimum.getXF());
    final float y = Math.max(v.getYF(), minimum.getYF());
    return new PVectorI2F<T>(x, y);
  }

  /**
   * Calculate the distance between the two vectors {@code v0} and {@code v1}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter.
   *
   * @return The distance between the two vectors
   */

  public static <T> double distance(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    return PVectorI2F.magnitude(PVectorI2F.subtract(v0, v1));
  }

  /**
   * Calculate the scalar product of the vectors {@code v0} and {@code v1}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter.
   *
   * @return The scalar product of the two vectors
   */

  public static <T> double dotProduct(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    final double x = (double) (v0.getXF() * v1.getXF());
    final double y = (double) (v0.getYF() * v1.getYF());
    return x + y;
  }

  /**
   * Linearly interpolate between {@code v0} and {@code v1} by the amount {@code
   * alpha}.
   *
   * The {@code alpha} parameter controls the degree of interpolation, such
   * that:
   *
   * <ul> <li>{@code interpolateLinear(v0, v1, 0.0) = v0}</li> <li>{@code
   * interpolateLinear(v0, v1, 1.0) = v1}</li> </ul>
   *
   * @param v0    The left input vector.
   * @param v1    The right input vector.
   * @param alpha The interpolation value, between {@code 0.0} and {@code 1.0}.
   * @param <T>   A phantom type parameter.
   *
   * @return {@code (1 - alpha) * v0 + alpha * v1}
   */

  public static <T> PVectorI2F<T> interpolateLinear(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1,
    final float alpha)
  {
    final PVectorReadable2FType<T> w0 =
      PVectorI2F.scale(v0, (double) (float) (1.0 - (double) alpha));
    final PVectorReadable2FType<T> w1 = PVectorI2F.scale(v1, (double) alpha);
    return PVectorI2F.add(w0, w1);
  }

  /**
   * Calculate the magnitude of the vector {@code v}.
   *
   * Correspondingly, {@code magnitude(normalize(v)) == 1.0}.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter.
   *
   * @return The magnitude of the input vector
   */

  public static <T> double magnitude(
    final PVectorReadable2FType<T> v)
  {
    return Math.sqrt(PVectorI2F.magnitudeSquared(v));
  }

  /**
   * Calculate the squared magnitude of the vector {@code v}.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter.
   *
   * @return The squared magnitude of the input vector
   */

  public static <T> double magnitudeSquared(
    final PVectorReadable2FType<T> v)
  {
    return PVectorI2F.dotProduct(v, v);
  }

  /**
   * Normalize the vector {@code v}, preserving its direction but reducing it to
   * unit length.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter.
   *
   * @return A vector with the same orientation as {@code v} but with magnitude
   * equal to {@code 1.0}
   */

  public static <T> PVectorI2F<T> normalize(
    final PVectorReadable2FType<T> v)
  {
    final double m = PVectorI2F.magnitudeSquared(v);
    if (m > 0.0) {
      final double sq = Math.sqrt(m);
      final double r = 1.0 / sq;
      return PVectorI2F.scale(v, r);
    }
    return new PVectorI2F<T>(v);
  }

  /**
   * <p> Orthonormalize and return the vectors {@code v0} and {@code v1} . </p>
   * <p> See <a href="http://en.wikipedia.org/wiki/Gram-Schmidt_process">GSP</a>
   * </p>
   *
   * @param v0  The left vector
   * @param v1  The right vector
   * @param <T> A phantom type parameter.
   *
   * @return A pair {@code (v0, v1)}, orthonormalized.
   *
   * @since 7.0.0
   */

  public static <T> Pair<PVectorI2F<T>, PVectorI2F<T>> orthoNormalize(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    final PVectorI2F<T> v0n = PVectorI2F.normalize(v0);
    final PVectorI2F<T> projection =
      PVectorI2F.scale(v0n, PVectorI2F.dotProduct(v1, v0n));
    final PVectorI2F<T> vr =
      PVectorI2F.normalize(PVectorI2F.subtract(v1, projection));
    return Pair.pair(v0n, vr);
  }

  /**
   * Calculate the projection of the vector {@code p} onto the vector {@code
   * q}.
   *
   * @param p   The left vector
   * @param q   The right vector
   * @param <T> A phantom type parameter.
   *
   * @return {@code ((dotProduct p q) / magnitudeSquared q) * q}
   */

  public static <T> PVectorI2F<T> projection(
    final PVectorReadable2FType<T> p,
    final PVectorReadable2FType<T> q)
  {
    final double dot = PVectorI2F.dotProduct(p, q);
    final double qms = PVectorI2F.magnitudeSquared(q);
    final double s = dot / qms;
    return PVectorI2F.scale(p, s);
  }

  /**
   * Scale the vector {@code v} by the scalar {@code r}.
   *
   * @param v   The input vector
   * @param r   The scaling value
   * @param <T> A phantom type parameter.
   *
   * @return {@code (v.x * r, v.y * r)}
   */

  public static <T> PVectorI2F<T> scale(
    final PVectorReadable2FType<T> v,
    final double r)
  {
    final double x = (double) v.getXF() * r;
    final double y = (double) v.getYF() * r;
    return new PVectorI2F<T>((float) x, (float) y);
  }

  /**
   * Subtract the vector {@code v1} from the vector {@code v0}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter.
   *
   * @return {@code (v0.x - v1.x, v0.y - v1.y)}
   */

  public static <T> PVectorI2F<T> subtract(
    final PVectorReadable2FType<T> v0,
    final PVectorReadable2FType<T> v1)
  {
    return new PVectorI2F<T>(v0.getXF() - v1.getXF(), v0.getYF() - v1.getYF());
  }

  /**
   * @param <T> A phantom type parameter.
   *
   * @return The zero vector.
   */

  @SuppressWarnings("unchecked") public static <T> PVectorI2F<T> zero()
  {
    return (PVectorI2F<T>) PVectorI2F.ZERO;
  }

  @Override public boolean equals(
    final @Nullable Object obj)
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
    final PVectorI2F<?> other = (PVectorI2F<?>) obj;
    if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
      return false;
    }
    return Float.floatToIntBits(this.y) == Float.floatToIntBits(other.y);
  }

  @Override public float getXF()
  {
    return this.x;
  }

  @Override public float getYF()
  {
    return this.y;
  }

  @Override public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + Float.floatToIntBits(this.x);
    result = (prime * result) + Float.floatToIntBits(this.y);
    return result;
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[PVectorI2F ");
    builder.append(this.x);
    builder.append(" ");
    builder.append(this.y);
    builder.append("]");
    final String r = builder.toString();
    return NullCheck.notNull(r);
  }
}