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

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jaux.ApproximatelyEqualFloat;

public class VectorI2FTest
{
  @SuppressWarnings("static-method") @Test public void testAbsoluteOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MIN_VALUE);

      Assert.assertTrue(VectorI2F.absolute(v).x >= 0.0f);
      Assert.assertTrue(VectorI2F.absolute(v).y >= 0.0f);
    }
  }

  @SuppressWarnings("static-method") @Test public void testAdd()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v0 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v1 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F vr = VectorI2F.add(v0, v1);

      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.x, v0.x
        + v1.x));
      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.y, v0.y
        + v1.y));
    }
  }

  @SuppressWarnings("static-method") @Test public void testAddScaled()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v0 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v1 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final float r = (float) (Math.random() * Float.MAX_VALUE);
      final VectorI2F vr = VectorI2F.addScaled(v0, v1, r);

      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.x, v0.x
        + (v1.x * r)));
      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.y, v0.y
        + (v1.y * r)));
    }
  }

  @SuppressWarnings("static-method") @Test public void testAngleDegrees0()
  {
    final VectorI2F v0 = new VectorI2F(0.0f, 1.0f);
    final VectorI2F v1 = new VectorI2F(1.0f, 0.0f);
    final float d = VectorI2F.angleDegrees(v0, v1);

    Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(d, 90.0f));
  }

  @SuppressWarnings("static-method") @Test public
    void
    testApproximatelyEqualTransitive0()
  {
    final float x0 = 0.0f;
    final float x1 = 0.0f;
    final float y0 = 0.0f;
    final float y1 = 0.0f;
    Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(x0, x1));
    Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(y0, y1));

    final VectorI2F v0 = new VectorI2F(x0, y0);
    final VectorI2F v1 = new VectorI2F(x1, y1);
    Assert.assertTrue(VectorI2F.approximatelyEqual(v0, v1));
  }

  @SuppressWarnings("static-method") @Test public
    void
    testApproximatelyEqualTransitive1()
  {
    final float x0 = 0.0f;
    final float x1 = 1.0f;
    final float y0 = 0.0f;
    final float y1 = 1.0f;
    Assert.assertFalse(ApproximatelyEqualFloat.approximatelyEqual(x0, x1));
    Assert.assertFalse(ApproximatelyEqualFloat.approximatelyEqual(y0, y1));

    final VectorI2F v0 = new VectorI2F(x0, y0);
    final VectorI2F v1 = new VectorI2F(x1, y1);
    Assert.assertFalse(VectorI2F.approximatelyEqual(v0, v1));
  }

  @SuppressWarnings("static-method") @Test public void testCheckInterface()
  {
    final VectorI2F v = new VectorI2F(3.0f, 5.0f);

    Assert.assertTrue(v.x == v.getXF());
    Assert.assertTrue(v.y == v.getYF());
  }

  @SuppressWarnings("static-method") @Test public
    void
    testClampByVectorMaximumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float max_x = (float) (Math.random() * Float.MIN_VALUE);
      final float max_y = (float) (Math.random() * Float.MIN_VALUE);
      final VectorI2F maximum = new VectorI2F(max_x, max_y);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MIN_VALUE);

      Assert
        .assertTrue(VectorI2F.clampMaximumByVector(v, maximum).x <= maximum.x);
      Assert
        .assertTrue(VectorI2F.clampMaximumByVector(v, maximum).y <= maximum.y);
    }
  }

  @SuppressWarnings("static-method") @Test public
    void
    testClampByVectorMinimumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float min_x = (float) (Math.random() * Float.MAX_VALUE);
      final float min_y = (float) (Math.random() * Float.MAX_VALUE);
      final VectorI2F minimum = new VectorI2F(min_x, min_y);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MIN_VALUE);

      Assert
        .assertTrue(VectorI2F.clampMinimumByVector(v, minimum).x >= minimum.x);
      Assert
        .assertTrue(VectorI2F.clampMinimumByVector(v, minimum).y >= minimum.y);
    }
  }

  @SuppressWarnings("static-method") @Test public
    void
    testClampByVectorOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F minimum =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MIN_VALUE);
      final VectorI2F maximum =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MAX_VALUE);

      Assert
        .assertTrue(VectorI2F.clampByVector(v, minimum, maximum).x <= maximum.x);
      Assert
        .assertTrue(VectorI2F.clampByVector(v, minimum, maximum).x >= minimum.x);
      Assert
        .assertTrue(VectorI2F.clampByVector(v, minimum, maximum).y <= maximum.y);
      Assert
        .assertTrue(VectorI2F.clampByVector(v, minimum, maximum).y >= minimum.y);
    }
  }

  @SuppressWarnings("static-method") @Test public
    void
    testClampMaximumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float maximum = (float) (Math.random() * Float.MIN_VALUE);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);

      Assert.assertTrue(VectorI2F.clampMaximum(v, maximum).x <= maximum);
      Assert.assertTrue(VectorI2F.clampMaximum(v, maximum).y <= maximum);
    }
  }

  @SuppressWarnings("static-method") @Test public
    void
    testClampMinimumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float minimum = (float) (Math.random() * Float.MAX_VALUE);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MIN_VALUE);

      Assert.assertTrue(VectorI2F.clampMinimum(v, minimum).x >= minimum);
      Assert.assertTrue(VectorI2F.clampMinimum(v, minimum).y >= minimum);
    }
  }

  @SuppressWarnings("static-method") @Test public void testClampOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final float minimum = (float) (Math.random() * Float.MIN_VALUE);
      final float maximum = (float) (Math.random() * Float.MAX_VALUE);
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MIN_VALUE, Math.random()
          * Float.MAX_VALUE);

      Assert.assertTrue(VectorI2F.clamp(v, minimum, maximum).x <= maximum);
      Assert.assertTrue(VectorI2F.clamp(v, minimum, maximum).x >= minimum);
      Assert.assertTrue(VectorI2F.clamp(v, minimum, maximum).y <= maximum);
      Assert.assertTrue(VectorI2F.clamp(v, minimum, maximum).y >= minimum);
    }
  }

  @SuppressWarnings("static-method") @Test public void testDistance()
  {
    final VectorI2F v0 = new VectorI2F(0.0f, 1.0f);
    final VectorI2F v1 = new VectorI2F(0.0f, 0.0f);

    Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(
      VectorI2F.distance(v0, v1),
      1.0f));
  }

  @SuppressWarnings("static-method") @Test public void testDistanceOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v0 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v1 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);

      Assert.assertTrue(VectorI2F.distance(v0, v1) >= 0.0f);
    }
  }

  @SuppressWarnings("static-method") @Test public
    void
    testDotProductOrthonormal()
  {
    final VectorI2F v = new VectorI2F(1.0, 0.0);
    Assert.assertTrue(VectorI2F.dotProduct(v, v) == 1.0);
  }

  @SuppressWarnings("static-method") @Test public void testEqualsCase0()
  {
    final VectorI2F m0 = new VectorI2F();
    Assert.assertTrue(m0.equals(m0));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsCase1()
  {
    final VectorI2F m0 = new VectorI2F();
    Assert.assertFalse(m0.equals(null));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsCase2()
  {
    final VectorI2F m0 = new VectorI2F();
    Assert.assertFalse(m0.equals(Integer.valueOf(23)));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsCase3()
  {
    final VectorI2F m0 = new VectorI2F();
    final VectorI2F m1 = new VectorI2F();
    Assert.assertTrue(m0.equals(m1));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsCorrect()
  {
    final VectorI2F v0 = new VectorI2F(0.0f, 0.0f);
    final VectorI2F v1 = new VectorI2F(0.0f, 0.0f);
    final VectorI2F vy = new VectorI2F(0.0f, 1.0f);
    final VectorI2F vx = new VectorI2F(1.0f, 0.0f);

    Assert.assertTrue(v0.equals(v0));
    Assert.assertFalse(v0.equals(null));
    Assert.assertFalse(v0.equals(Integer.valueOf(0)));
    Assert.assertFalse(v0.equals(vy));
    Assert.assertFalse(v0.equals(vx));
    Assert.assertTrue(v0.equals(v1));
  }

  @SuppressWarnings("static-method") @Test public void testHashCodeCorrect()
  {
    final VectorI2F v0 = new VectorI2F(0.0f, 0.0f);
    final VectorI2F v1 = new VectorI2F(0.0f, 0.0f);
    final VectorI2F vy = new VectorI2F(0.0f, 1.0f);
    final VectorI2F vx = new VectorI2F(1.0f, 0.0f);

    Assert.assertTrue(v0.hashCode() == v0.hashCode());
    Assert.assertTrue(v0.hashCode() == v1.hashCode());
    Assert.assertTrue(v0.hashCode() != vx.hashCode());
    Assert.assertTrue(v0.hashCode() != vy.hashCode());
  }

  @SuppressWarnings("static-method") @Test public
    void
    testInitializeReadable()
  {
    final VectorI2F v0 = new VectorI2F(1.0f, 2.0f);
    final VectorI2F v1 = new VectorI2F(v0);

    Assert.assertTrue(v0.x == v1.x);
    Assert.assertTrue(v0.y == v1.y);
  }

  @SuppressWarnings("static-method") @Test public
    void
    testInterpolateLinearLimits()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v0 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v1 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);

      Assert.assertTrue(VectorI2F.approximatelyEqual(
        VectorI2F.interpolateLinear(v0, v1, 0.0f),
        v0));
      Assert.assertTrue(VectorI2F.approximatelyEqual(
        VectorI2F.interpolateLinear(v0, v1, 1.0f),
        v1));
    }
  }

  @SuppressWarnings("static-method") @Test public void testMagnitudeNormal()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      ApproximatelyEqualFloat.approximatelyEqual(
        VectorI2F.magnitude(VectorI2F.normalize(v)),
        1.0f);
    }
  }

  @SuppressWarnings("static-method") @Test public void testNoargZero()
  {
    final VectorI2F v = new VectorI2F();
    VectorI2F.approximatelyEqual(v, VectorI2F.ZERO);
  }

  @SuppressWarnings("static-method") @Test public void testNormalizeZero()
  {
    final VectorI2F v0 = new VectorI2F(0.0f, 0.0f);
    VectorI2F.approximatelyEqual(VectorI2F.normalize(v0), v0);
  }

  @SuppressWarnings("static-method") @Test public
    void
    testProjectionPerpendicularZero()
  {
    {
      final VectorI2F p = new VectorI2F(1.0, 0.0);
      final VectorI2F q = new VectorI2F(0.0, 1.0);
      final VectorI2F r = VectorI2F.projection(p, q);
      Assert.assertTrue(VectorI2F.magnitude(r) == 0.0);
    }

    {
      final VectorI2F p = new VectorI2F(-1.0, 0.0);
      final VectorI2F q = new VectorI2F(0.0, 1.0);
      final VectorI2F r = VectorI2F.projection(p, q);
      Assert.assertTrue(VectorI2F.magnitude(r) == 0.0);
    }
  }

  @SuppressWarnings("static-method") @Test public void testString()
  {
    final VectorI2F v = new VectorI2F(0.0f, 1.0f);
    Assert.assertTrue(v.toString().equals("[VectorI2F 0.0 1.0]"));
  }

  @SuppressWarnings("static-method") @Test public void testSubtract()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final VectorI2F v0 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F v1 =
        new VectorI2F(Math.random() * Float.MAX_VALUE, Math.random()
          * Float.MAX_VALUE);
      final VectorI2F vr = VectorI2F.subtract(v0, v1);

      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.x, v0.x
        - v1.x));
      Assert.assertTrue(ApproximatelyEqualFloat.approximatelyEqual(vr.y, v0.y
        - v1.y));
    }
  }
}
