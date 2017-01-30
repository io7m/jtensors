/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
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
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;
import com.io7m.jtensors.VectorM2F;
import com.io7m.jtensors.VectorM3F;
import com.io7m.jtensors.VectorReadable2FType;
import com.io7m.jtensors.VectorReadable3FType;

/**
 * <p> A three-dimensional mutable vector type with {@code float} elements.
 * </p>
 *
 * <p> Values of this type cannot be accessed safely from multiple threads
 * without explicit synchronization. </p>
 *
 * @param <T> A phantom type parameter
 *
 * @since 7.0.0
 */

public final class PVectorM3F<T> implements PVector3FType<T>
{
  private float x;
  private float y;
  private float z;

  /**
   * Default constructor, initializing the vector with values {@code [0.0, 0.0,
   * 0.0]}.
   */

  public PVectorM3F()
  {

  }

  /**
   * Construct a vector initialized with the given values.
   *
   * @param in_x The {@code x} value
   * @param in_y The {@code y} value
   * @param in_z The {@code z} value
   */

  public PVectorM3F(
    final float in_x,
    final float in_y,
    final float in_z)
  {
    this.x = in_x;
    this.y = in_y;
    this.z = in_z;
  }

  /**
   * Construct a vector initialized with the values given in the vector {@code
   * in_v}.
   *
   * @param in_v The source vector
   */

  public PVectorM3F(
    final PVectorReadable3FType<T> in_v)
  {
    this.x = in_v.getXF();
    this.y = in_v.getYF();
    this.z = in_v.getZF();
  }

  /**
   * Calculate the absolute values of the elements in vector {@code v}, saving
   * the result to {@code out}.
   *
   * @param v   The input vector
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (abs v.x, abs v.y, abs v.z)}
   */

  public static <T, V extends PVectorWritable3FType<T>> V absolute(
    final PVectorReadable3FType<T> v,
    final V out)
  {
    final float x = Math.abs(v.getXF());
    final float y = Math.abs(v.getYF());
    final float z = Math.abs(v.getZF());
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Calculate the absolute values of the elements in vector {@code v},
   * modifying the vector in-place.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (abs v.x, abs v.y, abs v.z)}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V absoluteInPlace(
    final V v)
  {
    return absolute(v, v);
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and {@code v1},
   * saving the result to {@code out}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x + v1.x, v0.y + v1.y, v0.z + v1.z)}
   */

  public static <T, V extends PVectorWritable3FType<T>> V add(
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1,
    final V out)
  {
    final float x = v0.getXF() + v1.getXF();
    final float y = v0.getYF() + v1.getYF();
    final float z = v0.getZF() + v1.getZF();
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and {@code v1},
   * saving the result to {@code v0}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x + v1.x, v0.y + v1.y, v0.z + v1.z)}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V addInPlace(
    final V v0,
    final PVectorReadable3FType<T> v1)
  {
    return add(v0, v1, v0);
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and the
   * element-wise product of {@code v1} and {@code r}, saving the result to
   * {@code out}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param out The output vector
   * @param r   The scaling value
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x + (v1.x * r), v0.y + (v1.y * r), v0.z + (v1.z * r))}
   */

  public static <T, V extends PVectorWritable3FType<T>> V addScaled(
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1,
    final double r,
    final V out)
  {
    final float x = (float) ((double) v0.getXF() + ((double) v1.getXF() * r));
    final float y = (float) ((double) v0.getYF() + ((double) v1.getYF() * r));
    final float z = (float) ((double) v0.getZF() + ((double) v1.getZF() * r));
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Calculate the element-wise sum of the vectors {@code v0} and the
   * element-wise product of {@code v1} and {@code r}, saving the result to
   * {@code v0}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param r   The scaling value
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x + (v1.x * r), v0.y + (v1.y * r), v0.z + (v1.z * r))}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V addScaledInPlace(
    final V v0,
    final PVectorReadable3FType<T> v1,
    final double r)
  {
    return addScaled(v0, v1, r, v0);
  }

  /**
   * Determine whether or not the vectors {@code va} and {@code vb} are equal to
   * within the degree of error given in {@code context}.
   *
   * @param context The equality context
   * @param va      The left input vector
   * @param vb      The right input vector
   * @param <T>     A phantom type parameter
   *
   * @return {@code true} if the vectors are almost equal
   *
   * @see AlmostEqualFloat#almostEqual(AlmostEqualFloat.ContextRelative, float,
   * float)
   * @since 7.0.0
   */

  public static <T> boolean almostEqual(
    final AlmostEqualFloat.ContextRelative context,
    final PVectorReadable3FType<T> va,
    final PVectorReadable3FType<T> vb)
  {
    final boolean xs =
      AlmostEqualFloat.almostEqual(context, va.getXF(), vb.getXF());
    final boolean ys =
      AlmostEqualFloat.almostEqual(context, va.getYF(), vb.getYF());
    final boolean zs =
      AlmostEqualFloat.almostEqual(context, va.getZF(), vb.getZF());
    return xs && ys && zs;
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * maximum]} inclusive, saving the result to {@code out}.
   *
   * @param v       The input vector
   * @param minimum The minimum allowed value
   * @param maximum The maximum allowed value
   * @param out     The output vector
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at most {@code maximum} and at
   * least {@code minimum}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clamp(
    final PVectorReadable3FType<T> v,
    final float minimum,
    final float maximum,
    final V out)
  {
    final float x = Math.min(Math.max(v.getXF(), minimum), maximum);
    final float y = Math.min(Math.max(v.getYF(), minimum), maximum);
    final float z = Math.min(Math.max(v.getZF(), minimum), maximum);
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum} and {@code maximum}, saving
   * the result to {@code out}.
   *
   * @param v       The input vector
   * @param minimum The vector containing the minimum acceptable values
   * @param maximum The vector containing the maximum acceptable values
   * @param out     The output vector
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (min(max(v.x, minimum.x), maximum.x), min(max(v.y,
   * minimum.y), maximum.y), min(max(v.z, minimum.z), maximum.z))}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clampByPVector(
    final PVectorReadable3FType<T> v,
    final PVectorReadable3FType<T> minimum,
    final PVectorReadable3FType<T> maximum,
    final V out)
  {
    final float x =
      Math.min(Math.max(v.getXF(), minimum.getXF()), maximum.getXF());
    final float y =
      Math.min(Math.max(v.getYF(), minimum.getYF()), maximum.getYF());
    final float z =
      Math.min(Math.max(v.getZF(), minimum.getZF()), maximum.getZF());
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum} and {@code maximum}, saving
   * the result to {@code v}.
   *
   * @param v       The input vector
   * @param minimum The vector containing the minimum acceptable values
   * @param maximum The vector containing the maximum acceptable values
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (min(max(v.x, minimum.x), maximum.x), min(max(v.y,
   * minimum.y), maximum.y), min(max(v.z, minimum.z), maximum.z))}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampByPVectorInPlace(
    final V v,
    final PVectorReadable3FType<T> minimum,
    final PVectorReadable3FType<T> maximum)
  {
    return clampByPVector(v, minimum, maximum, v);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * maximum]} inclusive, saving the result to {@code v}.
   *
   * @param v       The input vector
   * @param minimum The minimum allowed value
   * @param maximum The maximum allowed value
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at most {@code maximum} and at
   * least {@code minimum}, in {@code v}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampInPlace(
    final V v,
    final float minimum,
    final float maximum)
  {
    return clamp(v, minimum, maximum, v);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [-Infinity
   * .. maximum]} inclusive, saving the result to {@code out}.
   *
   * @param v       The input vector
   * @param out     The output vector
   * @param maximum The maximum allowed value
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at most {@code maximum}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clampMaximum(
    final PVectorReadable3FType<T> v,
    final float maximum,
    final V out)
  {
    final float x = Math.min(v.getXF(), maximum);
    final float y = Math.min(v.getYF(), maximum);
    final float z = Math.min(v.getZF(), maximum);
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code maximum}, saving the result to {@code
   * out}.
   *
   * @param v       The input vector
   * @param maximum The vector containing the maximum acceptable values
   * @param out     The output vector
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (min(v.x, maximum.x), min(v.y, maximum.y), min(v.z,
   * maximum.z))}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clampMaximumByPVector(
    final PVectorReadable3FType<T> v,
    final PVectorReadable3FType<T> maximum,
    final V out)
  {
    final float x = Math.min(v.getXF(), maximum.getXF());
    final float y = Math.min(v.getYF(), maximum.getYF());
    final float z = Math.min(v.getZF(), maximum.getZF());
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code maximum}, saving the result to {@code
   * v}.
   *
   * @param v       The input vector
   * @param maximum The vector containing the maximum acceptable values
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (min(v.x, maximum.x), min(v.y, maximum.y), min(v.z,
   * maximum.z))}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampMaximumByPVectorInPlace(
    final V v,
    final PVectorReadable3FType<T> maximum)
  {
    return clampMaximumByPVector(v, maximum, v);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [-Infinity
   * .. maximum]} inclusive, saving the result to {@code v}.
   *
   * @param v       The input vector
   * @param maximum The maximum allowed value
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at most {@code maximum} , in
   * {@code v}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampMaximumInPlace(
    final V v,
    final float maximum)
  {
    return clampMaximum(v, maximum, v);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * Infinity]} inclusive, saving the result to {@code out}.
   *
   * @param v       The input vector
   * @param out     The output vector
   * @param minimum The minimum allowed value
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at least {@code minimum}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clampMinimum(
    final PVectorReadable3FType<T> v,
    final float minimum,
    final V out)
  {
    final float x = Math.max(v.getXF(), minimum);
    final float y = Math.max(v.getYF(), minimum);
    final float z = Math.max(v.getZF(), minimum);
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum}, saving the result to {@code
   * out}.
   *
   * @param v       The input vector
   * @param out     The output vector
   * @param minimum The vector containing the minimum acceptable values
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (max(v.x, minimum.x), max(v.y, minimum.y), max(v.z,
   * minimum.z))}
   */

  public static <T, V extends PVectorWritable3FType<T>> V clampMinimumByPVector(
    final PVectorReadable3FType<T> v,
    final PVectorReadable3FType<T> minimum,
    final V out)
  {
    final float x = Math.max(v.getXF(), minimum.getXF());
    final float y = Math.max(v.getYF(), minimum.getYF());
    final float z = Math.max(v.getZF(), minimum.getZF());
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Clamp the elements of the vector {@code v} to the inclusive range given by
   * the corresponding elements in {@code minimum}, saving the result to {@code
   * v}.
   *
   * @param v       The input vector
   * @param minimum The vector containing the minimum acceptable values
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return {@code (max(v.x, minimum.x), max(v.y, minimum.y), max(v.z,
   * minimum.z))} , in {@code v}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampMinimumByPVectorInPlace(
    final V v,
    final PVectorReadable3FType<T> minimum)
  {
    return clampMinimumByPVector(v, minimum, v);
  }

  /**
   * Clamp the elements of the vector {@code v} to the range {@code [minimum ..
   * Infinity]} inclusive, saving the result to {@code v}.
   *
   * @param v       The input vector
   * @param minimum The minimum allowed value
   * @param <T>     A phantom type parameter
   * @param <V>     The precise type of vector
   *
   * @return A vector with both elements equal to at least {@code minimum}, in
   * {@code v}.
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V clampMinimumInPlace(
    final V v,
    final float minimum)
  {
    return clampMinimum(v, minimum, v);
  }

  /**
   * Copy all elements of the vector {@code input} to the vector {@code
   * output}.
   *
   * @param <U>    The specific type of vector
   * @param input  The input vector
   * @param output The output vector
   * @param <T>    A phantom type parameter
   *
   * @return output
   */

  public static <T, U extends PVectorWritable3FType<T>> U copy(
    final PVectorReadable3FType<T> input,
    final U output)
  {
    output.set3F(input.getXF(), input.getYF(), input.getZF());
    return output;
  }

  /**
   * Return a vector perpendicular to both {@code v0} and {@code v1} , saving
   * the result in {@code out}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return out
   */

  public static <T, V extends PVectorWritable3FType<T>> V crossProduct(
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1,
    final V out)
  {
    final float x = (v0.getYF() * v1.getZF()) - (v0.getZF() * v1.getYF());
    final float y = (v0.getZF() * v1.getXF()) - (v0.getXF() * v1.getZF());
    final float z = (v0.getXF() * v1.getYF()) - (v0.getYF() * v1.getXF());
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Calculate the distance between the two vectors {@code v0} and {@code v1}.
   *
   * @param c   Preallocated storage
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter
   *
   * @return The distance between the two vectors
   *
   * @since 7.0.0
   */

  public static <T> double distance(
    final ContextPVM3F c,
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1)
  {
    final PVectorM3F<T> vr = (PVectorM3F<T>) c.va;
    return (double) magnitude(subtract(v0, v1, vr));
  }

  /**
   * Calculate the scalar product of the vectors {@code v0} and {@code v1}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter
   *
   * @return The scalar product of the two vectors
   */

  public static <T> double dotProduct(
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1)
  {
    final double x = (double) (v0.getXF() * v1.getXF());
    final double y = (double) (v0.getYF() * v1.getYF());
    final double z = (double) (v0.getZF() * v1.getZF());
    return x + y + z;
  }

  /**
   * Linearly interpolate between {@code v0} and {@code v1} by the amount {@code
   * alpha}, saving the result to {@code r}.
   *
   * The {@code alpha} parameter controls the degree of interpolation, such
   * that:
   *
   * <ul> <li>{@code interpolateLinear(v0, v1, 0.0, r) -> r = v0}</li>
   * <li>{@code interpolateLinear(v0, v1, 1.0, r) -> r = v1}</li> </ul>
   *
   * @param c     Preallocated storage
   * @param v0    The left input vector
   * @param v1    The right input vector
   * @param alpha The interpolation value, between {@code 0.0} and {@code 1.0}
   * @param r     The result vector
   * @param <T>   A phantom type parameter
   * @param <V>   The precise type of vector
   *
   * @return {@code r}
   *
   * @since 7.0.0
   */

  public static <T, V extends PVectorWritable3FType<T>> V interpolateLinear(
    final ContextPVM3F c,
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1,
    final double alpha,
    final V r)
  {
    final PVectorM3F<T> va = (PVectorM3F<T>) c.va;
    final PVectorM3F<T> vb = (PVectorM3F<T>) c.vb;
    scale(v0, 1.0 - alpha, va);
    scale(v1, alpha, vb);
    return add(va, vb, r);
  }

  /**
   * Calculate the magnitude of the vector {@code v}.
   *
   * Correspondingly, {@code magnitude(normalize(v)) == 1.0}.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter
   *
   * @return The magnitude of the input vector
   */

  public static <T> float magnitude(
    final PVectorReadable3FType<T> v)
  {
    return (float) Math.sqrt(magnitudeSquared(v));
  }

  /**
   * Calculate the squared magnitude of the vector {@code v}.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter
   *
   * @return The squared magnitude of the input vector
   */

  public static <T> double magnitudeSquared(
    final PVectorReadable3FType<T> v)
  {
    return dotProduct(v, v);
  }

  /**
   * Returns a vector with the same orientation as {@code v} but with magnitude
   * equal to {@code 1.0} in {@code out}. The function returns the zero vector
   * iff the input is the zero vector.
   *
   * @param v   The input vector
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return out
   */

  public static <T, V extends PVectorWritable3FType<T>> V normalize(
    final PVectorReadable3FType<T> v,
    final V out)
  {
    final double m = magnitudeSquared(v);
    if (m > 0.0) {
      final double reciprocal = 1.0 / Math.sqrt(m);
      return scale(v, reciprocal, out);
    }
    out.set3F(v.getXF(), v.getYF(), v.getZF());
    return out;
  }

  /**
   * Returns a vector with the same orientation as {@code v} but with magnitude
   * equal to {@code 1.0} in {@code v}. The function returns the zero vector iff
   * the input is the zero vector.
   *
   * @param v   The input vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return v
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V normalizeInPlace(
    final V v)
  {
    return normalize(v, v);
  }

  /**
   * <p> Orthonormalize and return the vectors {@code v0} and {@code v1} . </p>
   *
   * <p> See <a href="http://en.wikipedia.org/wiki/Gram-Schmidt_process">GSP</a>
   * </p>
   *
   * @param c      Preallocated storage
   * @param v0     The left vector
   * @param v0_out The orthonormalized form of {@code v0}
   * @param v1     The right vector
   * @param v1_out The orthonormalized form of {@code v1}
   * @param <T>    A phantom type parameter
   * @param <V>    The precise type of vector
   *
   * @since 7.0.0
   */

  public static <T, V extends PVectorWritable3FType<T>> void orthoNormalize(
    final ContextPVM3F c,
    final PVectorReadable3FType<T> v0,
    final V v0_out,
    final PVectorReadable3FType<T> v1,
    final V v1_out)
  {
    final PVectorM3F<T> va = (PVectorM3F<T>) c.va;
    final PVectorM3F<T> vb = (PVectorM3F<T>) c.vb;
    final PVectorM3F<T> vc = (PVectorM3F<T>) c.vc;
    normalize(v0, va);
    scale(va, dotProduct(v1, va), vb);
    normalizeInPlace(subtract(v1, vb, vc));
    copy(va, v0_out);
    copy(vc, v1_out);
  }

  /**
   * <p> Orthonormalize and the vectors {@code v0} and {@code v1}. </p> <p> See
   * <a href="http://en.wikipedia.org/wiki/Gram-Schmidt_process">GSP</a> </p>
   *
   * @param c   Preallocated storage
   * @param v0  The left vector
   * @param v1  The right vector
   * @param <V> The precise type of vector
   * @param <T> A phantom type parameter
   *
   * @since 7.0.0
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> void orthoNormalizeInPlace(
    final ContextPVM3F c,
    final V v0,
    final V v1)
  {
    final PVectorM3F<T> va = (PVectorM3F<T>) c.va;
    final PVectorM3F<T> vb = (PVectorM3F<T>) c.vb;
    final PVectorM3F<T> vc = (PVectorM3F<T>) c.vc;
    normalize(v0, va);
    scale(va, dotProduct(v1, va), vb);
    normalizeInPlace(subtract(v1, vb, vc));
    copy(va, v0);
    copy(vc, v1);
  }

  /**
   * Calculate the projection of the vector {@code p} onto the vector {@code q},
   * saving the result in {@code r}.
   *
   * @param p   The left vector
   * @param q   The right vector
   * @param r   The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code ((dotProduct p q) / magnitudeSquared q) * q}
   */

  public static <T, V extends PVectorWritable3FType<T>> V projection(
    final PVectorReadable3FType<T> p,
    final PVectorReadable3FType<T> q,
    final V r)
  {
    final double dot = dotProduct(p, q);
    final double qms = magnitudeSquared(q);
    final double s = dot / qms;

    return scale(p, s, r);
  }

  /**
   * Scale the vector {@code v} by the scalar {@code r}, saving the result to
   * {@code out}.
   *
   * @param v   The input vector
   * @param r   The scaling value
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v.x * r, v.y * r, v.z * r)}
   */

  public static <T, V extends PVectorWritable3FType<T>> V scale(
    final PVectorReadable3FType<T> v,
    final double r,
    final V out)
  {
    final double x = (double) v.getXF() * r;
    final double y = (double) v.getYF() * r;
    final double z = (double) v.getZF() * r;
    out.set3F((float) x, (float) y, (float) z);
    return out;
  }

  /**
   * Scale the vector {@code v} by the scalar {@code r}, saving the result to
   * {@code v}.
   *
   * @param v   The input vector
   * @param r   The scaling value
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v.x * r, v.y * r, v.z * r)}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V scaleInPlace(
    final V v,
    final double r)
  {
    return scale(v, r, v);
  }

  /**
   * Subtract the vector {@code v1} from the vector {@code v0}, saving the
   * result to {@code out}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param out The output vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x - v1.x, v0.y - v1.y, v0.z - v1.z)}
   */

  public static <T, V extends PVectorWritable3FType<T>> V subtract(
    final PVectorReadable3FType<T> v0,
    final PVectorReadable3FType<T> v1,
    final V out)
  {
    final float x = v0.getXF() - v1.getXF();
    final float y = v0.getYF() - v1.getYF();
    final float z = v0.getZF() - v1.getZF();
    out.set3F(x, y, z);
    return out;
  }

  /**
   * Subtract the vector {@code v1} from the vector {@code v0}, saving the
   * result to {@code v0}.
   *
   * @param v0  The left input vector
   * @param v1  The right input vector
   * @param <T> A phantom type parameter
   * @param <V> The precise type of vector
   *
   * @return {@code (v0.x - v1.x, v0.y - v1.y, v0.z - v1.z)}
   */

  public static <T, V extends PVectorWritable3FType<T> &
    PVectorReadable3FType<T>> V subtractInPlace(
    final V v0,
    final PVectorReadable3FType<T> v1)
  {
    return subtract(v0, v1, v0);
  }

  @Override
  public void copyFrom2F(
    final VectorReadable2FType in_v)
  {
    VectorM2F.copy(in_v, this);
  }

  @Override
  public void copyFrom3F(
    final VectorReadable3FType in_v)
  {
    VectorM3F.copy(in_v, this);
  }

  @Override
  public void copyFromTyped2F(
    final PVectorReadable2FType<T> in_v)
  {
    PVectorM2F.copy(in_v, this);
  }

  @Override
  public void copyFromTyped3F(
    final PVectorReadable3FType<T> in_v)
  {
    copy(in_v, this);
  }

  @Override
  public boolean equals(
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
    final PVectorM3F<?> other = (PVectorM3F<?>) obj;
    if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
      return false;
    }
    return Float.floatToIntBits(this.y) == Float.floatToIntBits(other.y) && Float.floatToIntBits(
      this.z) == Float.floatToIntBits(other.z);
  }

  @Override
  public float getXF()
  {
    return this.x;
  }

  @Override
  public void setXF(
    final float in_x)
  {
    this.x = in_x;
  }

  @Override
  public float getYF()
  {
    return this.y;
  }

  @Override
  public void setYF(
    final float in_y)
  {
    this.y = in_y;
  }

  @Override
  public float getZF()
  {
    return this.z;
  }

  @Override
  public void setZF(
    final float in_z)
  {
    this.z = in_z;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + Float.floatToIntBits(this.x);
    result = (prime * result) + Float.floatToIntBits(this.y);
    result = (prime * result) + Float.floatToIntBits(this.z);
    return result;
  }

  @Override
  public void set2F(
    final float in_x,
    final float in_y)
  {
    this.x = in_x;
    this.y = in_y;
  }

  @Override
  public void set3F(
    final float in_x,
    final float in_y,
    final float in_z)
  {
    this.x = in_x;
    this.y = in_y;
    this.z = in_z;
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[PVectorM3F ");
    builder.append(this.x);
    builder.append(" ");
    builder.append(this.y);
    builder.append(" ");
    builder.append(this.z);
    builder.append("]");
    final String r = builder.toString();
    return NullCheck.notNull(r);
  }

  /**
   * Preallocated storage to allow all vector functions to run without
   * allocating.
   *
   * @since 7.0.0
   */

  public static final class ContextPVM3F
  {
    private final PVectorM3F<?> va;
    private final PVectorM3F<?> vb;
    private final PVectorM3F<?> vc;

    /**
     * Construct preallocated storage.
     */

    public ContextPVM3F()
    {
      this.va = new PVectorM3F<Object>();
      this.vb = new PVectorM3F<Object>();
      this.vc = new PVectorM3F<Object>();
    }
  }
}
