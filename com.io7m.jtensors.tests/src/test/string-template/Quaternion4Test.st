Quaternion4Test(
  className,
  quaternionType,
  quaternionComponentType,
  scalarRealType,
  generatorType,
  testOpClass,
  matrix4x4Type,
  matrix4x4OpClass,
  matrix4x4GeneratorType,
  matrix4x4ParameterizedOpClass,
  matrix4x4ParameterizedType,
  vector3Type,
  vector3GeneratorType,
  vector3OpClass,
  opClass) ::= <<
/*
 * Copyright © 2017 \<code@io7m.com\> https://www.io7m.com
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

package com.io7m.jtensors.tests.core.parameterized.vectors;

import java.util.Objects;
import com.io7m.jtensors.tests.TestUtilities;
import com.io7m.junreachable.UnreachableCodeException;
import java.util.Optional;
import net.java.quickcheck.Generator;
import net.java.quickcheck.QuickCheck;
import net.java.quickcheck.characteristic.AbstractCharacteristic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.io7m.percentpass.extension.PercentPassing;

/**
 * \<p>Unit tests for {@link <quaternionType>} values.\</p>
 *
 * @since 8.0.0
 */

public final class <className>
{
  private static Generator\<<quaternionType>\> createGenerator()
  {
    final Object o_gen = <generatorType>.create();
    return (Generator\<<quaternionType>\>) o_gen;
  }

  private static Generator\<<vector3Type>\> createVector3Generator()
  {
    final Object o_gen = <vector3GeneratorType>.create();
    return (Generator\<<vector3Type>\>) o_gen;
  }

  private static Generator\<<matrix4x4Type>\> createMatrix4x4Generator()
  {
    final Object o_gen = <matrix4x4GeneratorType>.createOrthogonal();
    return (Generator\<<matrix4x4Type>\>) o_gen;
  }

  @Test
  public void testEqualsHashCodeToString()
  {
    final var q0 =
      <quaternionType>.of(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("2"),
        <testOpClass>.constant("3"));

    final var q1 =
      <quaternionType>.of(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("2"),
        <testOpClass>.constant("3"));

    final var q2 =
      <quaternionType>.of(
        <testOpClass>.constant("10"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("2"),
        <testOpClass>.constant("3"));

    final var v3 =
      <quaternionType>.of(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("11"),
        <testOpClass>.constant("2"),
        <testOpClass>.constant("3"));

    final var v4 =
      <quaternionType>.of(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("12"),
        <testOpClass>.constant("3"));

    final var v5 =
      <quaternionType>.of(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("2"),
        <testOpClass>.constant("13"));

    Assertions.assertEquals(q0, q0);
    Assertions.assertEquals(q0, q1);
    Assertions.assertNotEquals(q0, q2);
    Assertions.assertNotEquals(q0, v3);
    Assertions.assertNotEquals(q0, v4);
    Assertions.assertNotEquals(q0, v5);
    Assertions.assertNotEquals(q0, null);
    Assertions.assertNotEquals(q0, Integer.valueOf(23));

    Assertions.assertEquals(q0.toString(), q1.toString());
    Assertions.assertNotEquals(q0.toString(), q2.toString());
    Assertions.assertNotEquals(q0.toString(), v3.toString());
    Assertions.assertNotEquals(q0.toString(), v4.toString());
    Assertions.assertNotEquals(q0.toString(), v5.toString());

    Assertions.assertEquals(q0.hashCode(), q1.hashCode());
    Assertions.assertNotEquals(q0.hashCode(), q2.hashCode());
    Assertions.assertNotEquals(q0.hashCode(), v3.hashCode());
    Assertions.assertNotEquals(q0.hashCode(), v4.hashCode());
    Assertions.assertNotEquals(q0.hashCode(), v5.hashCode());

    <testOpClass>.checkEquals(<testOpClass>.constant("0"), q0.x());
    <testOpClass>.checkEquals(<testOpClass>.constant("1"), q0.y());
    <testOpClass>.checkEquals(<testOpClass>.constant("2"), q0.z());
    <testOpClass>.checkEquals(<testOpClass>.constant("3"), q0.w());
  }

  @Test
  public void testBuilder()
  {
    final var q0 =
      <quaternionType>.builder()
      .setX(<testOpClass>.constant("0"))
      .setY(<testOpClass>.constant("1"))
      .setZ(<testOpClass>.constant("2"))
      .setW(<testOpClass>.constant("3"))
      .build();

    <testOpClass>.checkEquals(<testOpClass>.constant("0"), q0.x());
    <testOpClass>.checkEquals(<testOpClass>.constant("1"), q0.y());
    <testOpClass>.checkEquals(<testOpClass>.constant("2"), q0.z());
    <testOpClass>.checkEquals(<testOpClass>.constant("3"), q0.w());

    {
      final var q1 =
        <quaternionType>.builder()
        .from(q0)
        .build();
      Assertions.assertEquals(q0, q1);
    }
  }

  @Test
  public void testBuilderFailure()
  {
    Assertions.assertThrows(IllegalStateException.class, () -> {
        final var q0 =
          <quaternionType>.builder()
          .build();
    });
  }

  @PercentPassing
  public void testAdd()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();
    final var q0 = gen.next();
    final var q1 = gen.next();

    var ex = (<quaternionComponentType>) <testOpClass>.add(q0.x(), q1.x());
    var ey = (<quaternionComponentType>) <testOpClass>.add(q0.y(), q1.y());
    var ez = (<quaternionComponentType>) <testOpClass>.add(q0.z(), q1.z());
    var ew = (<quaternionComponentType>) <testOpClass>.add(q0.w(), q1.w());

    final var q2 = <opClass>.add(q0, q1);
    <testOpClass>.checkAlmostEquals(ex, q2.x());
    <testOpClass>.checkAlmostEquals(ey, q2.y());
    <testOpClass>.checkAlmostEquals(ez, q2.z());
    <testOpClass>.checkAlmostEquals(ew, q2.w());
  }

  /**
   * ∀ q0 q1 q2. add(add(q0, q1), q2) == add(q0, add(q1, q2))
   */

  @PercentPassing
  public void testAddAssociative()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = gen.next();
    final var q2 = gen.next();

    final var qr0 = <opClass>.add(<opClass>.add(q0, q1), q2);
    final var qr1 = <opClass>.add(q0, <opClass>.add(q1, q2));

    <testOpClass>.checkAlmostEquals(qr0.x(), qr1.x());
    <testOpClass>.checkAlmostEquals(qr0.y(), qr1.y());
    <testOpClass>.checkAlmostEquals(qr0.z(), qr1.z());
    <testOpClass>.checkAlmostEquals(qr0.w(), qr1.w());
  }

  /**
   * ∀ q0 q1. add(q0, q1) == add(q1, q0)
   */

  @PercentPassing
  public void testAddCommutative()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = gen.next();

    final var qr0 = <opClass>.add(q0, q1);
    final var qr1 = <opClass>.add(q1, q0);

    <testOpClass>.checkAlmostEquals(qr0.x(), qr1.x());
    <testOpClass>.checkAlmostEquals(qr0.y(), qr1.y());
    <testOpClass>.checkAlmostEquals(qr0.z(), qr1.z());
    <testOpClass>.checkAlmostEquals(qr0.w(), qr1.w());
  }

  /**
   * ∀ q0. add(q0, negate(q0)) == (0, 0, 0, 0)
   */

  @PercentPassing
  public void testAddNegationZero()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var qr0 = <opClass>.add(q0, <opClass>.negate(q0));

    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.x());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.y());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.z());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.w());
  }

  @PercentPassing
  public void testConjugate()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();
    final var q0 = gen.next();

    final var q1 = <opClass>.conjugate(q0);
    <testOpClass>.checkAlmostEquals(<testOpClass>.negate(q0.x()), q1.x());
    <testOpClass>.checkAlmostEquals(<testOpClass>.negate(q0.y()), q1.y());
    <testOpClass>.checkAlmostEquals(<testOpClass>.negate(q0.z()), q1.z());
    <testOpClass>.checkAlmostEquals(q0.w(), q1.w());

    final var q2 = <opClass>.conjugate(q1);
    <testOpClass>.checkAlmostEquals(q0.x(), q2.x());
    <testOpClass>.checkAlmostEquals(q0.y(), q2.y());
    <testOpClass>.checkAlmostEquals(q0.z(), q2.z());
    <testOpClass>.checkAlmostEquals(q0.w(), q2.w());
  }

  @PercentPassing
  public void testDotProductSelfMagnitudeSquared()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();

    <testOpClass>.checkAlmostEquals(
      <opClass>.magnitudeSquared(q0),
      <opClass>.dotProduct(q0, q0));
  }

  @PercentPassing
  public void testInterpolateLinearLimits()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = gen.next();

    final var q2 =
      <opClass>.interpolateLinear(q0, q1, <testOpClass>.constant("0"));

    final var q3 =
      <opClass>.interpolateLinear(q0, q1, <testOpClass>.constant("1"));

    <testOpClass>.checkAlmostEquals(q0.x(), q2.x());
    <testOpClass>.checkAlmostEquals(q0.y(), q2.y());
    <testOpClass>.checkAlmostEquals(q0.z(), q2.z());
    <testOpClass>.checkAlmostEquals(q0.w(), q2.w());

    <testOpClass>.checkAlmostEquals(q1.x(), q3.x());
    <testOpClass>.checkAlmostEquals(q1.y(), q3.y());
    <testOpClass>.checkAlmostEquals(q1.z(), q3.z());
    <testOpClass>.checkAlmostEquals(q1.w(), q3.w());
  }

  @PercentPassing
  public void testInterpolateLinearSphericalLimits()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = <opClass>.normalize(gen.next());
    final var q1 = <opClass>.normalize(gen.next());

    final var q2 =
      <opClass>.interpolateSphericalLinear(q0, q1, <testOpClass>.constant("0"));

    final var q3 =
      <opClass>.interpolateSphericalLinear(q0, q1, <testOpClass>.constant("1"));

    if (!<opClass>.isNegationOf(<testOpClass>::almostEquals, q0, q2)) {
      <testOpClass>.checkAlmostEquals(q0.x(), q2.x());
      <testOpClass>.checkAlmostEquals(q0.y(), q2.y());
      <testOpClass>.checkAlmostEquals(q0.z(), q2.z());
      <testOpClass>.checkAlmostEquals(q0.w(), q2.w());
    }

    if (!<opClass>.isNegationOf(<testOpClass>::almostEquals, q1, q3)) {
      <testOpClass>.checkAlmostEquals(q1.x(), q3.x());
      <testOpClass>.checkAlmostEquals(q1.y(), q3.y());
      <testOpClass>.checkAlmostEquals(q1.z(), q3.z());
      <testOpClass>.checkAlmostEquals(q1.w(), q3.w());
    }
  }

  @Test
  public void testMagnitudeSquaredZero()
  {
    <testOpClass>.checkAlmostEquals(
      <testOpClass>.constant("0"),
      <opClass>.magnitudeSquared(<opClass>.zero()));
  }

  @Test
  public void testMagnitudeZero()
  {
    <testOpClass>.checkAlmostEquals(
      <testOpClass>.constant("0"),
      <opClass>.magnitude(<opClass>.zero()));
  }

  @Test
  public void testMagnitudeSquaredIdentity()
  {
    <testOpClass>.checkAlmostEquals(
      <testOpClass>.constant("1"),
      <opClass>.magnitudeSquared(<opClass>.identity()));
  }

  @Test
  public void testMagnitudeIdentity()
  {
    <testOpClass>.checkAlmostEquals(
      <testOpClass>.constant("1"),
      <opClass>.magnitude(<opClass>.identity()));
  }

  @PercentPassing
  public void testMagnitudeNormalizeOne()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var vn = <opClass>.normalize(q0);
    final <scalarRealType> m = <opClass>.magnitude(vn);

    if (<testOpClass>.compareReal(m, <testOpClass>.constant("0")) > 0) {
      <testOpClass>.checkAlmostEquals(<testOpClass>.constant("1"), m);
    } else {
      Assertions.assertEquals(q0, vn);
    }
  }

  /**
   * ∀ q0 q1 q2. multiply(multiply(q0, q1), q2) == multiply(q0, multiply(q1, q2))
   */

  @PercentPassing
  public void testMultiplyAssociative()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = gen.next();
    final var q2 = gen.next();

    final var qr0 = <opClass>.multiply(<opClass>.multiply(q0, q1), q2);
    final var qr1 = <opClass>.multiply(q0, <opClass>.multiply(q1, q2));

    <testOpClass>.checkAlmostEquals(qr0.x(), qr1.x());
    <testOpClass>.checkAlmostEquals(qr0.y(), qr1.y());
    <testOpClass>.checkAlmostEquals(qr0.z(), qr1.z());
    <testOpClass>.checkAlmostEquals(qr0.w(), qr1.w());
  }

  /**
   * ∀ q0. multiply(q0, conjugate(q0)) == (0, 0, 0, 1)
   */

  @PercentPassing
  public void testMultiplyConjugateIdentity()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = <opClass>.normalize(gen.next());
    final var qr0 = <opClass>.normalize(<opClass>.multiply(q0, <opClass>.conjugate(q0)));

    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.x());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.y());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), qr0.z());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("1"), qr0.w());
  }

  @Test
  public void testNormalizeZero()
  {
    Assertions.assertEquals(<opClass>.zero(), <opClass>.normalize(<opClass>.zero()));
  }

  /**
   * ∀ q0 x y z. normalize(ofAxisAngle(x, y, z, 0)) == (0, 0, 0, 1)
   */

  @PercentPassing
  public void testOfAxisAngleIdentity()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 =
      <opClass>.normalize(gen.next());
    final var q1 =
      <opClass>.normalize(
        <opClass>.ofAxisAngle(
          q0.x(), q0.y(), q0.z(), <testOpClass>.constant("0")));

    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.x());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.y());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.z());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("1"), q1.w());
  }

  @Test
  public void testMakeAxisAngleX_45()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("1"),
        <testOpClass>.constant("0"),
        <testOpClass>.constant("0"),
        <testOpClass>.radiansOfDegrees("45.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.3826834323650898, q.x());
    <testOpClass>.checkAlmostEquals(0.0f,               q.y());
    <testOpClass>.checkAlmostEquals(0.0f,               q.z());
    <testOpClass>.checkAlmostEquals(0.9238795325112867, q.w());
  }

  @Test
  public void testMakeAxisAngleX_90()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("1"),
        <testOpClass>.constant("0"),
        <testOpClass>.constant("0"),
        <testOpClass>.radiansOfDegrees("90.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.x());
    <testOpClass>.checkAlmostEquals(0.0,                q.y());
    <testOpClass>.checkAlmostEquals(0.0,                q.z());
    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.w());
  }

  @Test
  public void testMakeAxisAngleY_45()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("0"),
        <testOpClass>.radiansOfDegrees("45.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.0,                q.x());
    <testOpClass>.checkAlmostEquals(0.3826834323650898, q.y());
    <testOpClass>.checkAlmostEquals(0.0,                q.z());
    <testOpClass>.checkAlmostEquals(0.9238795325112867, q.w());
  }

  @Test
  public void testMakeAxisAngleY_90()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.constant("0"),
        <testOpClass>.radiansOfDegrees("90.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.0,                q.x());
    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.y());
    <testOpClass>.checkAlmostEquals(0.0,                q.z());
    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.w());
  }

  @Test
  public void testMakeAxisAngleZ_45()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.radiansOfDegrees("45.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.0,                q.x());
    <testOpClass>.checkAlmostEquals(0.0,                q.y());
    <testOpClass>.checkAlmostEquals(0.3826834323650898, q.z());
    <testOpClass>.checkAlmostEquals(0.9238795325112867, q.w());
  }

  @Test
  public void testMakeAxisAngleZ_90()
  {
    final var q =
      <opClass>.ofAxisAngle(
        <testOpClass>.constant("0"),
        <testOpClass>.constant("0"),
        <testOpClass>.constant("1"),
        <testOpClass>.radiansOfDegrees("90.0"));

    /*
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    <testOpClass>.checkAlmostEquals(0.0,                q.x());
    <testOpClass>.checkAlmostEquals(0.0,                q.y());
    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.z());
    <testOpClass>.checkAlmostEquals(0.7071067811865475, q.w());
  }

  @PercentPassing
  public void testScale()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final <scalarRealType> r = gen.next().x();

    var ex = (<quaternionComponentType>) <testOpClass>.multiplyReal(q0.x(), r);
    var ey = (<quaternionComponentType>) <testOpClass>.multiplyReal(q0.y(), r);
    var ez = (<quaternionComponentType>) <testOpClass>.multiplyReal(q0.z(), r);
    var ew = (<quaternionComponentType>) <testOpClass>.multiplyReal(q0.w(), r);

    final var q2 = <opClass>.scale(q0, r);
    <testOpClass>.checkAlmostEquals(ex, q2.x());
    <testOpClass>.checkAlmostEquals(ey, q2.y());
    <testOpClass>.checkAlmostEquals(ez, q2.z());
    <testOpClass>.checkAlmostEquals(ew, q2.w());
  }

  @PercentPassing
  public void testScaleZero()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = <opClass>.scale(q0, <testOpClass>.constant("0"));
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.x());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.y());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.z());
    <testOpClass>.checkAlmostEquals(<testOpClass>.constant("0"), q1.w());
  }

  @PercentPassing
  public void testScaleOne()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = <opClass>.scale(q0, <testOpClass>.constant("1"));
    Assertions.assertEquals(q0, q1);
  }

  @PercentPassing
  public void testSubtract()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var q0 = gen.next();
    final var q1 = gen.next();

    var ex = (<quaternionComponentType>) <testOpClass>.subtract(q0.x(), q1.x());
    var ey = (<quaternionComponentType>) <testOpClass>.subtract(q0.y(), q1.y());
    var ez = (<quaternionComponentType>) <testOpClass>.subtract(q0.z(), q1.z());
    var ew = (<quaternionComponentType>) <testOpClass>.subtract(q0.w(), q1.w());

    final var q2 = <opClass>.subtract(q0, q1);
    <testOpClass>.checkAlmostEquals(ex, q2.x());
    <testOpClass>.checkAlmostEquals(ey, q2.y());
    <testOpClass>.checkAlmostEquals(ez, q2.z());
    <testOpClass>.checkAlmostEquals(ew, q2.w());
  }

  @PercentPassing
  public void testToMatrix4x4Identity()
  {
    final var q = <opClass>.identity();
    final <matrix4x4Type> m = <opClass>.toMatrix4x4(q);
    <testOpClass>.checkAlmostEqualsMatrix(<matrix4x4OpClass>.identity(), m);
  }

  @PercentPassing
  public void testToMatrix4x4OfAxisAngleEquivalent()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> v_axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q =
      <opClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);
    final <matrix4x4Type> m_a =
      <opClass>.toMatrix4x4(q);
    final <matrix4x4Type> m_o =
      <matrix4x4OpClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);

    <testOpClass>.checkAlmostEqualsMatrix(m_o, m_a);
  }

  @PercentPassing
  public void testToMatrix4x4OfAxisAngleOrthogonal()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> v_axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q =
      <opClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);
    final <matrix4x4Type> m_a =
      <opClass>.toMatrix4x4(q);

    final Optional\<<matrix4x4Type>\> m_ai_opt =
      <matrix4x4OpClass>.invert(m_a);
    final <matrix4x4Type> m_ai =
      m_ai_opt.get();
    final <matrix4x4Type> m_at =
      <matrix4x4OpClass>.transpose(m_a);

    <testOpClass>.checkAlmostEqualsMatrix(m_at, m_ai);
  }

  @PercentPassing
  public void testToPMatrix4x4Identity()
  {
    final var q = <opClass>.identity();
    final <matrix4x4ParameterizedType> m = <opClass>.toPMatrix4x4(q);
    <testOpClass>.checkAlmostEqualsMatrix(<matrix4x4ParameterizedOpClass>.identity(), m);
  }

  @PercentPassing
  public void testToPMatrix4x4OfAxisAngleEquivalent()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> v_axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q =
      <opClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);
    final <matrix4x4ParameterizedType> m_a =
      <opClass>.toPMatrix4x4(q);
    final <matrix4x4ParameterizedType> m_o =
      <matrix4x4ParameterizedOpClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);

    <testOpClass>.checkAlmostEqualsMatrix(m_o, m_a);
  }

  @PercentPassing
  public void testToPMatrix4x4OfAxisAngleOrthogonal()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> v_axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q =
      <opClass>.ofAxisAngle(v_axis.x(), v_axis.y(), v_axis.z(), angle);
    final <matrix4x4ParameterizedType> m_a =
      <opClass>.toPMatrix4x4(q);

    final Optional\<<matrix4x4ParameterizedType>\> m_ai_opt =
      <matrix4x4ParameterizedOpClass>.invert(m_a);
    final <matrix4x4ParameterizedType> m_ai =
      m_ai_opt.get();
    final <matrix4x4ParameterizedType> m_at =
      <matrix4x4ParameterizedOpClass>.transpose(m_a);

    <testOpClass>.checkAlmostEqualsMatrix(m_at, m_ai);
  }

  /**
   * ∀ x y z a. ofMatrix4x4(Matrix.ofAxisAngle(ofAxisAngle(x, y, z, a))) ≅ ofAxisAngle(x, y, z, a)
   *
   * Where m ≅ n can mean that m is the negation of n.
   */

  @PercentPassing
  public void testOfMatrix4x4()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q0 =
      <opClass>.ofAxisAngle(axis.x(), axis.y(), axis.z(), angle);
    final <matrix4x4Type> m0 =
      <matrix4x4OpClass>.ofAxisAngle(axis.x(), axis.y(), axis.z(), angle);
    final var q1 =
      <opClass>.ofMatrix4x4(m0);

    if (<opClass>.isNegationOf(<testOpClass>::almostEquals, q0, q1)) {
      return;
    }

    <testOpClass>.checkAlmostEquals(q0.x(), q1.x());
    <testOpClass>.checkAlmostEquals(q0.y(), q1.y());
    <testOpClass>.checkAlmostEquals(q0.z(), q1.z());
    <testOpClass>.checkAlmostEquals(q0.w(), q1.w());
  }

  /**
   * ∀ x y z a. ofMatrix4x4(PMatrix.ofAxisAngle(ofAxisAngle(x, y, z, a))) ≅ ofAxisAngle(x, y, z, a)
   *
   * Where m ≅ n can mean that m is the negation of n.
   */

  @PercentPassing
  public void testOfPMatrix4x4()
  {
    final Generator\<<vector3Type>\> gen = createVector3Generator();
    final <vector3Type> axis = <vector3OpClass>.normalize(gen.next());
    final <scalarRealType> angle = gen.next().x();

    final var q0 =
      <opClass>.ofAxisAngle(axis.x(), axis.y(), axis.z(), angle);
    final <matrix4x4ParameterizedType> m0 =
      <matrix4x4ParameterizedOpClass>.ofAxisAngle(axis.x(), axis.y(), axis.z(), angle);
    final var q1 =
      <opClass>.ofPMatrix4x4(m0);

    if (<opClass>.isNegationOf(<testOpClass>::almostEquals, q0, q1)) {
      return;
    }

    <testOpClass>.checkAlmostEquals(q0.x(), q1.x());
    <testOpClass>.checkAlmostEquals(q0.y(), q1.y());
    <testOpClass>.checkAlmostEquals(q0.z(), q1.z());
    <testOpClass>.checkAlmostEquals(q0.w(), q1.w());
  }

  /**
   * ∀ q. isNegationOf(negate(q), q) == true
   */

  @PercentPassing
  public void testIsNegationOfNegated()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();
    final var q0 = gen.next();
    final var q1 = <opClass>.negate(q0);
    Assertions.assertTrue(<opClass>.isNegationOf((x,y) -> <testOpClass>.almostEquals(x, y), q0, q1));
  }

  @PercentPassing
  public void testWithXYZW()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var v0 = gen.next();
    final var v1 = gen.next();
    final var v2 = v0.withX(v1.x()).withY(v1.y()).withZ(v1.z()).withW(v1.w());
    Assertions.assertEquals(v1, v2);
  }

  @PercentPassing
  public void testCopyOf()
  {
    final Generator\<<quaternionType>\> gen = createGenerator();

    final var v0 = gen.next();
    final var v1 = <quaternionType>.copyOf(v0);
    Assertions.assertEquals(v0, v1);
  }
}
>>
