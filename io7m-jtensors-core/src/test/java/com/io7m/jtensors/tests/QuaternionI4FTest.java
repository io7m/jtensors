/*
 * Copyright © 2013 <code@io7m.com> http://io7m.com
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

package com.io7m.jtensors.tests;

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jequality.AlmostEqualFloat;
import com.io7m.jtensors.MatrixM3x3F;
import com.io7m.jtensors.MatrixM4x4F;
import com.io7m.jtensors.QuaternionI4F;
import com.io7m.jtensors.VectorI3F;
import com.io7m.jtensors.VectorM3F;
import com.io7m.jtensors.VectorReadable3FType;

public class QuaternionI4FTest extends QuaternionI4Contract
{
  private static final VectorReadable3FType AXIS_X = new VectorI3F(1, 0, 0);
  private static final VectorReadable3FType AXIS_Y = new VectorI3F(0, 1, 0);
  private static final VectorReadable3FType AXIS_Z = new VectorI3F(0, 0, 1);

  @Override @Test public void testAdd()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x0 = (float) (Math.random() * Float.MAX_VALUE);
      final float y0 = (float) (Math.random() * Float.MAX_VALUE);
      final float z0 = (float) (Math.random() * Float.MAX_VALUE);
      final float w0 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v0 = new QuaternionI4F(x0, y0, z0, w0);

      final float x1 = (float) (Math.random() * Float.MAX_VALUE);
      final float y1 = (float) (Math.random() * Float.MAX_VALUE);
      final float z1 = (float) (Math.random() * Float.MAX_VALUE);
      final float w1 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v1 = new QuaternionI4F(x1, y1, z1, w1);

      final QuaternionI4F vr = QuaternionI4F.add(v0, v1);

      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getXF(),
        v0.getXF() + v1.getXF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getYF(),
        v0.getYF() + v1.getYF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getZF(),
        v0.getZF() + v1.getZF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getWF(),
        v0.getWF() + v1.getWF()));
    }
  }

  @Override @Test public void testAlmostEqualNot()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    final float x = (float) Math.random();
    final float y = x + 1.0f;
    final float z = y + 1.0f;
    final float w = z + 1.0f;
    final float q = w + 1.0f;

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, z, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, q, z, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, q, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, z, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, z, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, q, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, z, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, q, w);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, z, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, q, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, q, q, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, q, q);
      Assert.assertFalse(QuaternionI4F.almostEqual(ec, m0, m1));
    }
  }

  @Override @Test public void testAlmostEqualTransitive()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x0 = (float) (Math.random() * Float.MAX_VALUE);
      final float y0 = (float) (Math.random() * Float.MAX_VALUE);
      final float z0 = (float) (Math.random() * Float.MAX_VALUE);
      final float w0 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v0 = new QuaternionI4F(x0, y0, z0, w0);
      final QuaternionI4F v1 = new QuaternionI4F(x0, y0, z0, w0);
      final QuaternionI4F v2 = new QuaternionI4F(x0, y0, z0, w0);

      Assert.assertTrue(QuaternionI4F.almostEqual(ec, v0, v1));
      Assert.assertTrue(QuaternionI4F.almostEqual(ec, v1, v2));
      Assert.assertTrue(QuaternionI4F.almostEqual(ec, v0, v2));
    }
  }

  @Override @Test public void testCheckInterface()
  {
    final QuaternionI4F v = new QuaternionI4F(3.0f, 5.0f, 7.0f, 11.0f);

    Assert.assertTrue(v.getXF() == v.getXF());
    Assert.assertTrue(v.getYF() == v.getYF());
    Assert.assertTrue(v.getZF() == v.getZF());
    Assert.assertTrue(v.getWF() == v.getWF());
  }

  @Override @Test public void testConjugate()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final QuaternionI4F e = new QuaternionI4F(-1.0f, -2.0f, -3.0f, 4.0f);
    final QuaternionI4F q = new QuaternionI4F(1.0f, 2.0f, 3.0f, 4.0f);
    final QuaternionI4F r = QuaternionI4F.conjugate(q);
    final boolean t = QuaternionI4F.almostEqual(context, e, r);

    Assert.assertTrue(t);
  }

  @Override @Test public void testConjugateInvertible()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    boolean eq = false;

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) ((Math.random() * 200) - 100);
      final float y = (float) ((Math.random() * 200) - 100);
      final float z = (float) ((Math.random() * 200) - 100);
      final float w = (float) ((Math.random() * 200) - 100);

      final QuaternionI4F q = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F qc0 = QuaternionI4F.conjugate(q);
      final QuaternionI4F qc1 = QuaternionI4F.conjugate(qc0);

      eq = AlmostEqualFloat.almostEqual(context, q.getXF(), qc1.getXF());
      Assert.assertTrue(eq);
      eq = AlmostEqualFloat.almostEqual(context, q.getYF(), qc1.getYF());
      Assert.assertTrue(eq);
      eq = AlmostEqualFloat.almostEqual(context, q.getZF(), qc1.getZF());
      Assert.assertTrue(eq);
      eq = AlmostEqualFloat.almostEqual(context, q.getWF(), qc1.getWF());
      Assert.assertTrue(eq);
    }
  }

  @Override @Test public void testDefault0001()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();
    final QuaternionI4F v = new QuaternionI4F();
    QuaternionI4F.almostEqual(context, v, QuaternionI4F.IDENTITY);
  }

  @Override @Test public void testDotProduct()
  {
    final QuaternionI4F v0 = new QuaternionI4F(10.0f, 10.0f, 10.0f, 10.0f);
    final QuaternionI4F v1 = new QuaternionI4F(10.0f, 10.0f, 10.0f, 10.0f);

    {
      final double p = QuaternionI4F.dotProduct(v0, v1);
      Assert.assertTrue(v0.getXF() == 10.0f);
      Assert.assertTrue(v0.getYF() == 10.0f);
      Assert.assertTrue(v0.getZF() == 10.0f);
      Assert.assertTrue(v0.getWF() == 10.0f);
      Assert.assertTrue(v1.getXF() == 10.0f);
      Assert.assertTrue(v1.getYF() == 10.0f);
      Assert.assertTrue(v1.getZF() == 10.0f);
      Assert.assertTrue(v1.getWF() == 10.0f);
      Assert.assertTrue(p == 400.0f);
    }

    {
      final double p = QuaternionI4F.dotProduct(v0, v0);
      Assert.assertTrue(v0.getXF() == 10.0f);
      Assert.assertTrue(v0.getYF() == 10.0f);
      Assert.assertTrue(v0.getZF() == 10.0f);
      Assert.assertTrue(v0.getWF() == 10.0f);
      Assert.assertTrue(p == 400.0f);
    }

    {
      final double p = QuaternionI4F.dotProduct(v1, v1);
      Assert.assertTrue(v1.getXF() == 10.0f);
      Assert.assertTrue(v1.getYF() == 10.0f);
      Assert.assertTrue(v1.getZF() == 10.0f);
      Assert.assertTrue(v1.getWF() == 10.0f);
      Assert.assertTrue(p == 400.0f);
    }
  }

  @Override @Test public void testDotProductSelf()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) Math.random();
      final float y = (float) Math.random();
      final float z = (float) Math.random();
      final float w = (float) Math.random();
      final QuaternionI4F q = new QuaternionI4F(x, y, z, w);
      final double dp = QuaternionI4F.dotProduct(q, q);

      System.out.println("q  : " + q);
      System.out.println("dp : " + dp);

      AlmostEqualDouble.almostEqual(ec, 1.0, dp);
    }
  }

  @Override @Test public void testDotProductSelfMagnitudeSquared()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) Math.random();
      final float y = (float) Math.random();
      final float z = (float) Math.random();
      final float w = (float) Math.random();
      final QuaternionI4F q = new QuaternionI4F(x, y, z, w);

      final double ms = QuaternionI4F.magnitudeSquared(q);
      final double dp = QuaternionI4F.dotProduct(q, q);

      System.out.println("q  : " + q);
      System.out.println("ms : " + ms);
      System.out.println("dp : " + dp);

      AlmostEqualDouble.almostEqual(ec, ms, dp);
    }
  }

  @Override @Test public void testEqualsCorrect()
  {
    final QuaternionI4F v0 = new QuaternionI4F(0.0f, 0.0f, 0.0f, 0.0f);
    final QuaternionI4F v1 = new QuaternionI4F(0.0f, 0.0f, 0.0f, 0.0f);
    final QuaternionI4F vw = new QuaternionI4F(0.0f, 0.0f, 0.0f, 1.0f);
    final QuaternionI4F vz = new QuaternionI4F(0.0f, 0.0f, 1.0f, 0.0f);
    final QuaternionI4F vy = new QuaternionI4F(0.0f, 1.0f, 0.0f, 0.0f);
    final QuaternionI4F vx = new QuaternionI4F(1.0f, 0.0f, 0.0f, 0.0f);

    Assert.assertTrue(v0.equals(v0));
    Assert.assertFalse(v0.equals(null));
    Assert.assertFalse(v0.equals(Integer.valueOf(0)));
    Assert.assertFalse(v0.equals(vw));
    Assert.assertFalse(v0.equals(vz));
    Assert.assertFalse(v0.equals(vy));
    Assert.assertFalse(v0.equals(vx));
    Assert.assertTrue(v0.equals(v1));
  }

  @Override @Test public void testEqualsNotEqualCorrect()
  {
    final float x = (float) Math.random();
    final float y = x + 1.0f;
    final float z = y + 1.0f;
    final float w = z + 1.0f;
    final float q = w + 1.0f;

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      Assert.assertFalse(m0.equals(null));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      Assert.assertFalse(m0.equals(Integer.valueOf(23)));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, q, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, y, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(q, q, q, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, q, q, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(x, y, z, w);
      final QuaternionI4F m1 = new QuaternionI4F(x, y, q, q);
      Assert.assertFalse(m0.equals(m1));
    }
  }

  @Override @Test public void testHashCodeEqualsCorrect()
  {
    final QuaternionI4F m0 = new QuaternionI4F();
    final QuaternionI4F m1 = new QuaternionI4F();
    Assert.assertEquals(m0.hashCode(), m1.hashCode());
  }

  @Override @Test public void testHashCodeNotEqualCorrect()
  {
    {
      final QuaternionI4F m0 = new QuaternionI4F(23, 0, 0, 1);
      final QuaternionI4F m1 = new QuaternionI4F();
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(0, 23, 0, 1);
      final QuaternionI4F m1 = new QuaternionI4F();
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(0, 0, 23, 1);
      final QuaternionI4F m1 = new QuaternionI4F();
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final QuaternionI4F m0 = new QuaternionI4F(0, 0, 0, 23);
      final QuaternionI4F m1 = new QuaternionI4F();
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }
  }

  @Override @Test public void testInitializeReadable()
  {
    final QuaternionI4F v0 = new QuaternionI4F(1.0f, 2.0f, 3.0f, 4.0f);
    final QuaternionI4F v1 = new QuaternionI4F(v0);

    Assert.assertTrue(v0.getXF() == v1.getXF());
    Assert.assertTrue(v0.getYF() == v1.getYF());
    Assert.assertTrue(v0.getZF() == v1.getZF());
    Assert.assertTrue(v0.getWF() == v1.getWF());
  }

  @Override @Test public void testInterpolateLinearLimits()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x0 = (float) (Math.random() * Float.MAX_VALUE);
      final float y0 = (float) (Math.random() * Float.MAX_VALUE);
      final float z0 = (float) (Math.random() * Float.MAX_VALUE);
      final float w0 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v0 = new QuaternionI4F(x0, y0, z0, w0);

      final float x1 = (float) (Math.random() * Float.MAX_VALUE);
      final float y1 = (float) (Math.random() * Float.MAX_VALUE);
      final float z1 = (float) (Math.random() * Float.MAX_VALUE);
      final float w1 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v1 = new QuaternionI4F(x1, y1, z1, w1);

      Assert.assertTrue(QuaternionI4F.almostEqual(
        context,
        QuaternionI4F.interpolateLinear(v0, v1, 0.0f),
        v0));
      Assert.assertTrue(QuaternionI4F.almostEqual(
        context,
        QuaternionI4F.interpolateLinear(v0, v1, 1.0f),
        v1));
    }
  }

  @Override @Test public void testLookAtConsistent_Origin_NegativeX()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F.Context mc = new MatrixM4x4F.Context();
    final QuaternionI4F.Context qc = new QuaternionI4F.Context();

    final MatrixM4x4F mr = new MatrixM4x4F();
    final MatrixM4x4F mqr = new MatrixM4x4F();

    final VectorReadable3FType origin = new VectorI3F(0, 0, 0);
    final VectorReadable3FType target = new VectorI3F(-1, 0, 0);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM4x4F.lookAtWithContext(mc, origin, target, axis, mr);
    final QuaternionI4F q =
      QuaternionI4F.lookAtWithContext(qc, origin, target, axis);
    QuaternionI4F.makeRotationMatrix4x4(q, mqr);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mqr: ");
    System.out.println(mqr);

    boolean eq = false;

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mqr.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(ec, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testLookAtConsistent_Origin_PositiveX()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F.Context mc = new MatrixM4x4F.Context();
    final QuaternionI4F.Context qc = new QuaternionI4F.Context();

    final MatrixM4x4F mr = new MatrixM4x4F();
    final MatrixM4x4F mqr = new MatrixM4x4F();

    final VectorReadable3FType origin = new VectorI3F(0, 0, 0);
    final VectorReadable3FType target = new VectorI3F(1, 0, 0);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM4x4F.lookAtWithContext(mc, origin, target, axis, mr);
    final QuaternionI4F q =
      QuaternionI4F.lookAtWithContext(qc, origin, target, axis);
    QuaternionI4F.makeRotationMatrix4x4(q, mqr);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mqr: ");
    System.out.println(mqr);

    boolean eq = false;

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mqr.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(ec, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testLookAtMatrixEquivalentAxisY()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    final QuaternionI4F.Context qc = new QuaternionI4F.Context();
    final MatrixM4x4F.Context mc = new MatrixM4x4F.Context();

    final MatrixM4x4F ml = new MatrixM4x4F();
    final MatrixM4x4F mq = new MatrixM4x4F();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float origin_x =
        (float) ((Math.random() * 100) - (Math.random() * 100));
      final float origin_y =
        (float) ((Math.random() * 100) - (Math.random() * 100));
      final float origin_z =
        (float) ((Math.random() * 100) - (Math.random() * 100));

      final float target_x =
        (float) ((Math.random() * 100) - (Math.random() * 100));
      final float target_y =
        (float) ((Math.random() * 100) - (Math.random() * 100));
      final float target_z =
        (float) ((Math.random() * 100) - (Math.random() * 100));

      final VectorI3F origin = new VectorI3F(origin_x, origin_y, origin_z);
      final VectorI3F target = new VectorI3F(target_x, target_y, target_z);

      MatrixM4x4F.lookAtWithContext(
        mc,
        origin,
        target,
        QuaternionI4FTest.AXIS_Y,
        ml);
      final QuaternionI4F lq =
        QuaternionI4F.lookAtWithContext(
          qc,
          origin,
          target,
          QuaternionI4FTest.AXIS_Y);
      QuaternionI4F.makeRotationMatrix4x4(lq, mq);

      System.out.println("ml : ");
      System.out.println(ml);
      System.out.println("mq : ");
      System.out.println(mq);

      for (int row = 0; row < 3; ++row) {
        for (int col = 0; col < 3; ++col) {
          final float x = ml.getRowColumnF(row, col);
          final float y = mq.getRowColumnF(row, col);

          final boolean eq = AlmostEqualFloat.almostEqual(ec, x, y);
          Assert.assertTrue(eq);
        }
      }
    }
  }

  @Override @Test public void testMagnitudeNonzero()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) (1.0f + (Math.random() * Float.MAX_VALUE));
      final float y = (float) (1.0f + (Math.random() * Float.MAX_VALUE));
      final float z = (float) (1.0f + (Math.random() * Float.MAX_VALUE));
      final float w = (float) (1.0f + (Math.random() * Float.MAX_VALUE));
      final QuaternionI4F v = new QuaternionI4F(x, y, z, w);

      final double m = QuaternionI4F.magnitude(v);
      Assert.assertTrue(m > 0.0f);
    }
  }

  @Override @Test public void testMagnitudeNormal()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext6dp();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float max = 10000.0f;
      final float x = (float) (1.0f + (Math.random() * max));
      final float y = (float) (1.0f + (Math.random() * max));
      final float z = (float) (1.0f + (Math.random() * max));
      final float w = (float) (1.0f + (Math.random() * max));
      final QuaternionI4F v = new QuaternionI4F(x, y, z, w);

      final QuaternionI4F vr = QuaternionI4F.normalize(v);
      Assert.assertNotSame(v, vr);

      final double m = QuaternionI4F.magnitude(vr);

      System.out.println("v  : " + v);
      System.out.println("vr : " + vr);
      System.out.println("m  : " + m);

      Assert.assertTrue(AlmostEqualDouble.almostEqual(ec, m, 1.0));
    }
  }

  @Override @Test public void testMagnitudeNormalizeZero()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    final QuaternionI4F v = new QuaternionI4F(0.0f, 0.0f, 0.0f, 0.0f);
    final QuaternionI4F vr = QuaternionI4F.normalize(v);
    final double m = QuaternionI4F.magnitude(vr);
    Assert.assertTrue(AlmostEqualDouble.almostEqual(ec, m, 0.0));
  }

  @Override @Test public void testMagnitudeOne()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    final QuaternionI4F v = new QuaternionI4F(1.0f, 0.0f, 0.0f, 0.0f);
    final double m = QuaternionI4F.magnitude(v);
    Assert.assertTrue(AlmostEqualDouble.almostEqual(ec, m, 1.0));
  }

  @Override @Test public void testMagnitudeSimple()
  {
    final QuaternionI4F v = new QuaternionI4F(8.0f, 0.0f, 0.0f, 0.0f);

    {
      final double p = QuaternionI4F.dotProduct(v, v);
      final double q = QuaternionI4F.magnitudeSquared(v);
      final double r = QuaternionI4F.magnitude(v);
      Assert.assertTrue(p == 64.0f);
      Assert.assertTrue(q == 64.0f);
      Assert.assertTrue(r == 8.0f);
    }
  }

  @Override @Test public void testMagnitudeZero()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    final QuaternionI4F v = new QuaternionI4F(0.0f, 0.0f, 0.0f, 0.0f);
    final double m = QuaternionI4F.magnitude(v);
    Assert.assertTrue(AlmostEqualDouble.almostEqual(ec, m, 0.0));
  }

  @Override @Test public void testMakeAxisAngleNormal()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI3F axis_r =
        new VectorI3F(
          (float) Math.random(),
          (float) Math.random(),
          (float) Math.random());
      final VectorI3F axis_n = VectorI3F.normalize(axis_r);

      final QuaternionI4F q =
        QuaternionI4F.makeFromAxisAngle(
          axis_n,
          (float) Math.toRadians(Math.random() * 360));

      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        QuaternionI4F.magnitude(q),
        1.0f));

      System.out.println("testMakeAxisAngleNormal: " + q);
    }
  }

  @Override @Test public void testMakeAxisAngleX_45()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(45));

    System.out.println("testMakeAxisAngleX: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getXF(),
      0.3826834323650898f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getYF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getZF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.9238795325112867f));
  }

  @Override @Test public void testMakeAxisAngleX_90()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(90));

    System.out.println("testMakeAxisAngleX: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getXF(),
      0.7071067811865475f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getYF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getZF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.7071067811865475f));
  }

  @Override @Test public void testMakeAxisAngleY_45()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(45));

    System.out.println("testMakeAxisAngleY: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getXF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getYF(),
      0.3826834323650898f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getZF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.9238795325112867f));
  }

  @Override @Test public void testMakeAxisAngleY_90()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(90));

    System.out.println("testMakeAxisAngleY: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getXF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getYF(),
      0.7071067811865475f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getZF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.7071067811865475f));
  }

  @Override @Test public void testMakeAxisAngleZ_45()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(45));

    System.out.println("testMakeAxisAngleZ: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getXF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getYF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getZF(),
      0.3826834323650898f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.9238795325112867f));
  }

  @Override @Test public void testMakeAxisAngleZ_90()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;
    final QuaternionI4F q =
      QuaternionI4F.makeFromAxisAngle(axis, (float) Math.toRadians(90));

    System.out.println("testMakeAxisAngleZ: " + q);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getXF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(context, q.getYF(), 0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getZF(),
      0.7071067811865475f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      q.getWF(),
      0.7071067811865475f));
  }

  @Override @Test public void testMakeFromMatrix3x3Exhaustive()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();
    final AlmostEqualDouble.ContextRelative context_d =
      TestUtilities.getDoubleEqualityContext6dp();

    final MatrixM3x3F m = new MatrixM3x3F();
    boolean eq = false;

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final double degrees = (2 * Math.random() * 360.0f) - 360.0;
      final double angle = Math.toRadians(degrees);
      final float axis_x = (float) Math.random();
      final float axis_y = (float) Math.random();
      final float axis_z = (float) Math.random();
      final VectorM3F axis = new VectorM3F(axis_x, axis_y, axis_z);
      VectorM3F.normalizeInPlace(axis);

      /**
       * Produce a quaternion from an axis and angle.
       */

      final QuaternionI4F qaa = QuaternionI4F.makeFromAxisAngle(axis, angle);

      /**
       * Produce a rotation matrix from an axis and angle, and then a
       * quaternion from that matrix.
       */

      MatrixM3x3F.makeRotationInto(angle, axis, m);
      final QuaternionI4F qfm = QuaternionI4F.makeFromRotationMatrix3x3(m);

      final double mag_qfm = QuaternionI4F.magnitude(qfm);
      final double mag_qaa = QuaternionI4F.magnitude(qaa);

      System.out.println("mag_qfm : " + mag_qfm);
      System.out.println("mag_qaa : " + mag_qaa);
      System.out.println("axis    : " + axis);
      System.out.println("angle   : " + angle);
      System.out.println("m       : ");
      System.out.println(m);
      System.out.println("qfm     : " + qfm);
      System.out.println("qaa     : " + qaa);
      System.out.println("--");

      /**
       * The resulting quaternions are unit quaternions.
       */

      eq = AlmostEqualDouble.almostEqual(context_d, mag_qfm, 1.0);
      Assert.assertTrue(eq);
      eq = AlmostEqualDouble.almostEqual(context_d, mag_qaa, 1.0);
      Assert.assertTrue(eq);

      /**
       * The resulting quaternions match.
       */

      if (QuaternionI4F.almostEqual(context_f, qfm, qaa)) {
        Assert.assertTrue(true);
        continue;
      }

      /**
       * The sign of quaternions may flip when created from matrices.
       */

      if (QuaternionI4F.isNegationOf(context_f, qfm, qaa)) {
        Assert.assertTrue(true);
        continue;
      }

      Assert.fail(qfm + " != " + qaa);
    }
  }

  @Override @Test public void testMakeFromMatrix4x4Exhaustive()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();
    final AlmostEqualDouble.ContextRelative context_d =
      TestUtilities.getDoubleEqualityContext6dp();

    final MatrixM4x4F m = new MatrixM4x4F();
    boolean eq = false;

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final double degrees = (2 * Math.random() * 360.0f) - 360.0;
      final double angle = Math.toRadians(degrees);
      final float axis_x = (float) Math.random();
      final float axis_y = (float) Math.random();
      final float axis_z = (float) Math.random();
      final VectorM3F axis = new VectorM3F(axis_x, axis_y, axis_z);
      VectorM3F.normalizeInPlace(axis);

      /**
       * Produce a quaternion from an axis and angle.
       */

      final QuaternionI4F qaa = QuaternionI4F.makeFromAxisAngle(axis, angle);

      /**
       * Produce a rotation matrix from an axis and angle, and then a
       * quaternion from that matrix.
       */

      MatrixM4x4F.makeRotationInto(angle, axis, m);
      final QuaternionI4F qfm = QuaternionI4F.makeFromRotationMatrix4x4(m);

      final double mag_qfm = QuaternionI4F.magnitude(qfm);
      final double mag_qaa = QuaternionI4F.magnitude(qaa);

      System.out.println("mag_qfm : " + mag_qfm);
      System.out.println("mag_qaa : " + mag_qaa);
      System.out.println("axis    : " + axis);
      System.out.println("angle   : " + angle);
      System.out.println("m       : ");
      System.out.println(m);
      System.out.println("qfm     : " + qfm);
      System.out.println("qaa     : " + qaa);
      System.out.println("--");

      /**
       * The resulting quaternions are unit quaternions.
       */

      eq = AlmostEqualDouble.almostEqual(context_d, mag_qfm, 1.0);
      Assert.assertTrue(eq);
      eq = AlmostEqualDouble.almostEqual(context_d, mag_qaa, 1.0);
      Assert.assertTrue(eq);

      /**
       * The resulting quaternions match.
       */

      if (QuaternionI4F.almostEqual(context_f, qfm, qaa)) {
        Assert.assertTrue(true);
        continue;
      }

      /**
       * The sign of quaternions may flip when created from matrices.
       */

      if (QuaternionI4F.isNegationOf(context_f, qfm, qaa)) {
        Assert.assertTrue(true);
        continue;
      }

      Assert.fail(qfm + " != " + qaa);
    }
  }

  @Override @Test public void testMakeMatrix3x3_45X()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix3x3_45Y()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix3x3_45Z()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix3x3_Identity()
  {
    final QuaternionI4F q = new QuaternionI4F();
    final MatrixM3x3F m = new MatrixM3x3F();

    QuaternionI4F.makeRotationMatrix3x3(q, m);

    Assert.assertTrue(1.0 == m.getRowColumnF(0, 0));
    Assert.assertTrue(0.0 == m.getRowColumnF(0, 1));
    Assert.assertTrue(0.0 == m.getRowColumnF(0, 2));

    Assert.assertTrue(0.0 == m.getRowColumnF(1, 0));
    Assert.assertTrue(1.0 == m.getRowColumnF(1, 1));
    Assert.assertTrue(0.0 == m.getRowColumnF(1, 2));

    Assert.assertTrue(0.0 == m.getRowColumnF(2, 0));
    Assert.assertTrue(0.0 == m.getRowColumnF(2, 1));
    Assert.assertTrue(1.0 == m.getRowColumnF(2, 2));
  }

  @Override @Test public void testMakeMatrix3x3_Minus45X()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix3x3_Minus45Y()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix3x3_Minus45Z()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM3x3F mq = new MatrixM3x3F();
    final MatrixM3x3F mr = new MatrixM3x3F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;

    MatrixM3x3F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix3x3(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_45X()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_45Y()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_45Z()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_Identity()
  {
    final QuaternionI4F q = new QuaternionI4F();
    final MatrixM4x4F m = new MatrixM4x4F();

    QuaternionI4F.makeRotationMatrix4x4(q, m);

    Assert.assertTrue(1.0 == m.getRowColumnF(0, 0));
    Assert.assertTrue(0.0 == m.getRowColumnF(0, 1));
    Assert.assertTrue(0.0 == m.getRowColumnF(0, 2));
    Assert.assertTrue(0.0 == m.getRowColumnF(0, 3));

    Assert.assertTrue(0.0 == m.getRowColumnF(1, 0));
    Assert.assertTrue(1.0 == m.getRowColumnF(1, 1));
    Assert.assertTrue(0.0 == m.getRowColumnF(1, 2));
    Assert.assertTrue(0.0 == m.getRowColumnF(1, 3));

    Assert.assertTrue(0.0 == m.getRowColumnF(2, 0));
    Assert.assertTrue(0.0 == m.getRowColumnF(2, 1));
    Assert.assertTrue(1.0 == m.getRowColumnF(2, 2));
    Assert.assertTrue(0.0 == m.getRowColumnF(2, 3));

    Assert.assertTrue(0.0 == m.getRowColumnF(3, 0));
    Assert.assertTrue(0.0 == m.getRowColumnF(3, 1));
    Assert.assertTrue(0.0 == m.getRowColumnF(3, 2));
    Assert.assertTrue(1.0 == m.getRowColumnF(3, 3));
  }

  @Override @Test public void testMakeMatrix4x4_Minus45X()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_X;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_Minus45Y()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Y;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMakeMatrix4x4_Minus45Z()
  {
    final AlmostEqualFloat.ContextRelative context_f =
      TestUtilities.getSingleEqualityContext();

    final MatrixM4x4F mq = new MatrixM4x4F();
    final MatrixM4x4F mr = new MatrixM4x4F();
    boolean eq = false;

    final double radians = Math.toRadians(-45);
    final VectorReadable3FType axis = QuaternionI4FTest.AXIS_Z;

    MatrixM4x4F.makeRotationInto(radians, axis, mr);
    final QuaternionI4F q = QuaternionI4F.makeFromAxisAngle(axis, radians);
    QuaternionI4F.makeRotationMatrix4x4(q, mq);

    System.out.println("mr: ");
    System.out.println(mr);
    System.out.println("mq: ");
    System.out.println(mq);

    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final float x = mr.getRowColumnF(row, col);
        final float y = mq.getRowColumnF(row, col);
        eq = AlmostEqualFloat.almostEqual(context_f, x, y);
        Assert.assertTrue(eq);
      }
    }
  }

  @Override @Test public void testMultiply()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final VectorReadable3FType axis_x = QuaternionI4FTest.AXIS_X;
    final VectorReadable3FType axis_y = QuaternionI4FTest.AXIS_Y;
    final QuaternionI4F qx =
      QuaternionI4F.makeFromAxisAngle(axis_x, (float) Math.toRadians(45));
    final QuaternionI4F qy =
      QuaternionI4F.makeFromAxisAngle(axis_y, (float) Math.toRadians(45));

    /**
     * The quaternion resulting from a 45 degree rotation around the global X
     * axis, followed by a 45 degree rotation around the global Y axis.
     */

    final QuaternionI4F qr = QuaternionI4F.multiply(qy, qx);
    System.out.println("testMultiply: " + qr);

    /**
     * Values obtained by checking against the results produced by Blender.
     *
     * @see http://blender.org
     */

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      qr.getXF(),
      0.3535533905932738f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      qr.getYF(),
      0.3535533905932738f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      qr.getZF(),
      -0.14644660940672624f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      context,
      qr.getWF(),
      0.8535533905932737f));
  }

  @Override @Test public void testNegation()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final double x = (Math.random() * 2) - Math.random();
      final double y = (Math.random() * 2) - Math.random();
      final double z = (Math.random() * 2) - Math.random();
      final double w = (Math.random() * 2) - Math.random();
      final QuaternionI4F qi =
        new QuaternionI4F((float) x, (float) y, (float) z, (float) w);
      final QuaternionI4F qn =
        new QuaternionI4F((float) -x, (float) -y, (float) -z, (float) -w);
      final QuaternionI4F qr = QuaternionI4F.negate(qi);

      System.out.println("qi : " + qi);
      System.out.println("qn : " + qn);
      System.out.println("qr : " + qr);
      System.out.println("--");

      Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qr));
      Assert.assertTrue(QuaternionI4F.almostEqual(context, qn, qr));
    }
  }

  @Override @Test public void testNegationCases()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    final QuaternionI4F qi = new QuaternionI4F(1, 2, 3, 4);
    final QuaternionI4F qnx = new QuaternionI4F(-1, 2, 3, 4);
    final QuaternionI4F qny = new QuaternionI4F(-1, -2, 3, 4);
    final QuaternionI4F qnz = new QuaternionI4F(-1, -2, -3, 4);
    final QuaternionI4F qnw = new QuaternionI4F(-1, -2, -3, -4);

    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qi) == false);
    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qnx) == false);
    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qny) == false);
    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qnz) == false);
    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qnw) == true);

    final QuaternionI4F qnwn = QuaternionI4F.negate(qnw);
    Assert.assertTrue(QuaternionI4F.isNegationOf(context, qi, qnwn) == false);
    Assert.assertTrue(QuaternionI4F.almostEqual(context, qi, qnwn));
  }

  @Override @Test public void testNormalizeSimple()
  {
    final QuaternionI4F v0 = new QuaternionI4F(8.0f, 0.0f, 0.0f, 0.0f);
    final QuaternionI4F vr = QuaternionI4F.normalize(v0);
    final float m = QuaternionI4F.magnitude(vr);
    Assert.assertTrue(m == 1.0f);
  }

  @Override @Test public void testNormalizeZero()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    final QuaternionI4F q = new QuaternionI4F(0, 0, 0, 0);
    final QuaternionI4F qr = QuaternionI4F.normalize(q);

    Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, 0, qr.getXF()));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, 0, qr.getYF()));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, 0, qr.getZF()));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, 0, qr.getWF()));
  }

  @Override @Test public void testScaleOne()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) (Math.random() * Float.MAX_VALUE);
      final float y = (float) (Math.random() * Float.MAX_VALUE);
      final float z = (float) (Math.random() * Float.MAX_VALUE);
      final float w = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v = new QuaternionI4F(x, y, z, w);

      final QuaternionI4F vr = QuaternionI4F.scale(v, 1.0f);

      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        ec,
        v.getXF(),
        vr.getXF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        ec,
        v.getYF(),
        vr.getYF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        ec,
        v.getZF(),
        vr.getZF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        ec,
        v.getWF(),
        vr.getWF()));
    }
  }

  @Override @Test public void testScaleZero()
  {
    final AlmostEqualFloat.ContextRelative ec =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x = (float) (Math.random() * Float.MAX_VALUE);
      final float y = (float) (Math.random() * Float.MAX_VALUE);
      final float z = (float) (Math.random() * Float.MAX_VALUE);
      final float w = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v = new QuaternionI4F(x, y, z, w);

      final QuaternionI4F vr = QuaternionI4F.scale(v, 0.0f);

      Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, vr.getXF(), 0.0f));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, vr.getYF(), 0.0f));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, vr.getZF(), 0.0f));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(ec, vr.getWF(), 0.0f));
    }
  }

  @Override @Test public void testString()
  {
    final QuaternionI4F v = new QuaternionI4F(0.0f, 1.0f, 2.0f, 3.0f);
    Assert.assertTrue(v.toString().equals("[QuaternionI4F 0.0 1.0 2.0 3.0]"));
  }

  @Override @Test public void testSubtract()
  {
    final AlmostEqualFloat.ContextRelative context =
      TestUtilities.getSingleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float x0 = (float) (Math.random() * Float.MAX_VALUE);
      final float y0 = (float) (Math.random() * Float.MAX_VALUE);
      final float z0 = (float) (Math.random() * Float.MAX_VALUE);
      final float w0 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v0 = new QuaternionI4F(x0, y0, z0, w0);

      final float x1 = (float) (Math.random() * Float.MAX_VALUE);
      final float y1 = (float) (Math.random() * Float.MAX_VALUE);
      final float z1 = (float) (Math.random() * Float.MAX_VALUE);
      final float w1 = (float) (Math.random() * Float.MAX_VALUE);
      final QuaternionI4F v1 = new QuaternionI4F(x1, y1, z1, w1);

      final QuaternionI4F vr = QuaternionI4F.subtract(v0, v1);

      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getXF(),
        v0.getXF() - v1.getXF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getYF(),
        v0.getYF() - v1.getYF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getZF(),
        v0.getZF() - v1.getZF()));
      Assert.assertTrue(AlmostEqualFloat.almostEqual(
        context,
        vr.getWF(),
        v0.getWF() - v1.getWF()));
    }
  }
}
