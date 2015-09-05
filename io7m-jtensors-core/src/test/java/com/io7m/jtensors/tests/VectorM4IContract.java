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

import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jtensors.VectorM4I;
import org.junit.Assert;
import org.junit.Test;

public abstract class VectorM4IContract extends VectorM4Contract
{
  public static int randomNegativeNumber()
  {
    return (int) (Math.random() * Integer.MIN_VALUE);
  }

  public static int randomPositiveNumber()
  {
    return (int) (Math.random() * Integer.MAX_VALUE);
  }

  public static int randomPositiveSmallNumber()
  {
    return (int) (Math.random() * (1 << 14));
  }

  @Override @Test public void testAbsolute()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x = (int) (Math.random() * Integer.MIN_VALUE);
      final int y = (int) (Math.random() * Integer.MIN_VALUE);
      final int z = (int) (Math.random() * Integer.MIN_VALUE);
      final int w = (int) (Math.random() * Integer.MIN_VALUE);
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      VectorM4I.absolute(v, vr);

      Assert.assertEquals(Math.abs(v.getXI()), vr.getXI());
      Assert.assertEquals(Math.abs(v.getYI()), vr.getYI());
      Assert.assertEquals(Math.abs(v.getZI()), vr.getZI());
      Assert.assertEquals(Math.abs(v.getWI()), vr.getWI());
    }
  }

  protected abstract VectorM4I newVectorM4I(
    final int x,
    final int y,
    final int z,
    final int w);

  @Override @Test public void testAbsoluteMutation()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x = (int) (Math.random() * Integer.MIN_VALUE);
      final int y = (int) (Math.random() * Integer.MIN_VALUE);
      final int z = (int) (Math.random() * Integer.MIN_VALUE);
      final int w = (int) (Math.random() * Integer.MIN_VALUE);
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      VectorM4I.absoluteInPlace(v);

      Assert.assertEquals(v.getXI(), Math.abs(x));
      Assert.assertEquals(v.getYI(), Math.abs(y));
      Assert.assertEquals(v.getZI(), Math.abs(z));
      Assert.assertEquals(v.getWI(), Math.abs(w));
    }
  }

  @Override @Test public void testAdd()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x0 = VectorM4IContract.randomPositiveSmallNumber();
      final int y0 = VectorM4IContract.randomPositiveSmallNumber();
      final int z0 = VectorM4IContract.randomPositiveSmallNumber();
      final int w0 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v0 = this.newVectorM4I(x0, y0, z0, w0);

      final int x1 = VectorM4IContract.randomPositiveSmallNumber();
      final int y1 = VectorM4IContract.randomPositiveSmallNumber();
      final int z1 = VectorM4IContract.randomPositiveSmallNumber();
      final int w1 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v1 = this.newVectorM4I(x1, y1, z1, w1);

      final VectorM4I vr0 = this.newVectorM4I();
      VectorM4I.add(v0, v1, vr0);

      Assert.assertTrue(vr0.getXI() == (v0.getXI() + v1.getXI()));
      Assert.assertTrue(vr0.getYI() == (v0.getYI() + v1.getYI()));
      Assert.assertTrue(vr0.getZI() == (v0.getZI() + v1.getZI()));
      Assert.assertTrue(vr0.getWI() == (v0.getWI() + v1.getWI()));

      {
        final int orig_x = v0.getXI();
        final int orig_y = v0.getYI();
        final int orig_z = v0.getZI();
        final int orig_w = v0.getWI();
        VectorM4I.addInPlace(v0, v1);

        Assert.assertTrue(v0.getXI() == (orig_x + v1.getXI()));
        Assert.assertTrue(v0.getYI() == (orig_y + v1.getYI()));
        Assert.assertTrue(v0.getZI() == (orig_z + v1.getZI()));
        Assert.assertTrue(v0.getWI() == (orig_w + v1.getWI()));
      }
    }
  }

  @Override @Test public void testAddMutation()
  {
    final VectorM4I out = this.newVectorM4I();
    final VectorM4I v0 = this.newVectorM4I(1, 1, 1, 1);
    final VectorM4I v1 = this.newVectorM4I(1, 1, 1, 1);

    Assert.assertTrue(out.getXI() == 0);
    Assert.assertTrue(out.getYI() == 0);
    Assert.assertTrue(out.getZI() == 0);
    Assert.assertTrue(out.getWI() == 1);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);

    final VectorM4I ov0 = VectorM4I.add(v0, v1, out);

    Assert.assertTrue(out == ov0);
    Assert.assertTrue(out.getXI() == 2);
    Assert.assertTrue(out.getYI() == 2);
    Assert.assertTrue(out.getZI() == 2);
    Assert.assertTrue(out.getWI() == 2);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);

    final VectorM4I ov1 = VectorM4I.addInPlace(v0, v1);

    Assert.assertTrue(ov1 == v0);
    Assert.assertTrue(ov1.getXI() == 2);
    Assert.assertTrue(ov1.getYI() == 2);
    Assert.assertTrue(ov1.getZI() == 2);
    Assert.assertTrue(ov1.getWI() == 2);
    Assert.assertTrue(v0.getXI() == 2);
    Assert.assertTrue(v0.getYI() == 2);
    Assert.assertTrue(v0.getZI() == 2);
    Assert.assertTrue(v0.getWI() == 2);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);
  }

  @Override @Test public void testAddScaled()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x0 = VectorM4IContract.randomPositiveSmallNumber();
      final int y0 = VectorM4IContract.randomPositiveSmallNumber();
      final int z0 = VectorM4IContract.randomPositiveSmallNumber();
      final int w0 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v0 = this.newVectorM4I(x0, y0, z0, w0);

      final int x1 = VectorM4IContract.randomPositiveSmallNumber();
      final int y1 = VectorM4IContract.randomPositiveSmallNumber();
      final int z1 = VectorM4IContract.randomPositiveSmallNumber();
      final int w1 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v1 = this.newVectorM4I(x1, y1, z1, w1);

      final int r = VectorM4IContract.randomPositiveSmallNumber();

      final VectorM4I vr0 = this.newVectorM4I();
      VectorM4I.addScaled(v0, v1, r, vr0);

      Assert.assertTrue(vr0.getXI() == (v0.getXI() + (v1.getXI() * r)));
      Assert.assertTrue(vr0.getYI() == (v0.getYI() + (v1.getYI() * r)));
      Assert.assertTrue(vr0.getZI() == (v0.getZI() + (v1.getZI() * r)));
      Assert.assertTrue(vr0.getWI() == (v0.getWI() + (v1.getWI() * r)));

      {
        final int orig_x = v0.getXI();
        final int orig_y = v0.getYI();
        final int orig_z = v0.getZI();
        final int orig_w = v0.getWI();
        VectorM4I.addScaledInPlace(v0, v1, r);

        Assert.assertTrue(v0.getXI() == (orig_x + (v1.getXI() * r)));
        Assert.assertTrue(v0.getYI() == (orig_y + (v1.getYI() * r)));
        Assert.assertTrue(v0.getZI() == (orig_z + (v1.getZI() * r)));
        Assert.assertTrue(v0.getWI() == (orig_w + (v1.getWI() * r)));
      }
    }
  }

  @Override @Test public void testAlmostEqualNot()
  {
    // Not supported by integer vectors
  }

  @Override @Test public void testAlmostEqualTransitive()
  {
    // Not supported by integer vectors
  }

  @Override @Test public void testCheckInterface()
  {
    final VectorM4I v = this.newVectorM4I(3, 5, 7, 11);

    Assert.assertTrue(v.getXI() == v.getXI());
    Assert.assertTrue(v.getYI() == v.getYI());
    Assert.assertTrue(v.getZI() == v.getZI());
    Assert.assertTrue(v.getWI() == v.getWI());
  }

  @Override @Test public void testClampByVectorMaximumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int max_x = VectorM4IContract.randomNegativeNumber();
      final int max_y = VectorM4IContract.randomNegativeNumber();
      final int max_z = VectorM4IContract.randomNegativeNumber();
      final int max_w = VectorM4IContract.randomNegativeNumber();
      final VectorM4I maximum = this.newVectorM4I(max_x, max_y, max_z, max_w);

      final int x = VectorM4IContract.randomNegativeNumber();
      final int y = VectorM4IContract.randomNegativeNumber();
      final int z = VectorM4IContract.randomNegativeNumber();
      final int w = VectorM4IContract.randomNegativeNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      final VectorM4I vo = VectorM4I.clampMaximumByVector(v, maximum, vr);

      Assert.assertTrue(vo == vr);
      Assert.assertTrue(vr.getXI() <= maximum.getXI());
      Assert.assertTrue(vr.getYI() <= maximum.getYI());
      Assert.assertTrue(vr.getZI() <= maximum.getZI());
      Assert.assertTrue(vr.getWI() <= maximum.getWI());

      {
        final VectorM4I vr0 = VectorM4I.clampMaximumByVectorInPlace(v, maximum);
        Assert.assertTrue(vr0 == v);
        Assert.assertTrue(v.getXI() <= maximum.getXI());
        Assert.assertTrue(v.getYI() <= maximum.getYI());
        Assert.assertTrue(v.getZI() <= maximum.getZI());
        Assert.assertTrue(v.getWI() <= maximum.getWI());
      }
    }
  }

  @Override @Test public void testClampByVectorMinimumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int min_x = VectorM4IContract.randomPositiveNumber();
      final int min_y = VectorM4IContract.randomPositiveNumber();
      final int min_z = VectorM4IContract.randomPositiveNumber();
      final int min_w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I minimum = this.newVectorM4I(min_x, min_y, min_z, min_w);

      final int x = VectorM4IContract.randomNegativeNumber();
      final int y = VectorM4IContract.randomNegativeNumber();
      final int z = VectorM4IContract.randomNegativeNumber();
      final int w = VectorM4IContract.randomNegativeNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      final VectorM4I vo = VectorM4I.clampMinimumByVector(v, minimum, vr);

      Assert.assertTrue(vo == vr);
      Assert.assertTrue(vr.getXI() >= minimum.getXI());
      Assert.assertTrue(vr.getYI() >= minimum.getYI());
      Assert.assertTrue(vr.getZI() >= minimum.getZI());
      Assert.assertTrue(vr.getWI() >= minimum.getWI());

      {
        final VectorM4I vr0 = VectorM4I.clampMinimumByVectorInPlace(v, minimum);
        Assert.assertTrue(vr0 == v);
        Assert.assertTrue(v.getXI() >= minimum.getXI());
        Assert.assertTrue(v.getYI() >= minimum.getYI());
        Assert.assertTrue(v.getZI() >= minimum.getZI());
        Assert.assertTrue(v.getWI() >= minimum.getWI());
      }
    }
  }

  @Override @Test public void testClampByVectorOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int min_x = VectorM4IContract.randomNegativeNumber();
      final int min_y = VectorM4IContract.randomNegativeNumber();
      final int min_z = VectorM4IContract.randomNegativeNumber();
      final int min_w = VectorM4IContract.randomNegativeNumber();
      final VectorM4I minimum = this.newVectorM4I(min_x, min_y, min_z, min_w);

      final int max_x = VectorM4IContract.randomPositiveNumber();
      final int max_y = VectorM4IContract.randomPositiveNumber();
      final int max_z = VectorM4IContract.randomPositiveNumber();
      final int max_w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I maximum = this.newVectorM4I(max_x, max_y, max_z, max_w);

      final int x = VectorM4IContract.randomNegativeNumber();
      final int y = VectorM4IContract.randomPositiveNumber();
      final int z = VectorM4IContract.randomPositiveNumber();
      final int w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      final VectorM4I vo = VectorM4I.clampByVector(v, minimum, maximum, vr);

      Assert.assertTrue(vo == vr);
      Assert.assertTrue(vr.getXI() <= maximum.getXI());
      Assert.assertTrue(vr.getYI() <= maximum.getYI());
      Assert.assertTrue(vr.getZI() <= maximum.getZI());
      Assert.assertTrue(vr.getWI() <= maximum.getWI());
      Assert.assertTrue(vr.getXI() >= minimum.getXI());
      Assert.assertTrue(vr.getYI() >= minimum.getYI());
      Assert.assertTrue(vr.getZI() >= minimum.getZI());
      Assert.assertTrue(vr.getWI() >= minimum.getWI());

      {
        final VectorM4I vr0 =
          VectorM4I.clampByVectorInPlace(v, minimum, maximum);
        Assert.assertTrue(vr0 == v);
        Assert.assertTrue(v.getXI() <= maximum.getXI());
        Assert.assertTrue(v.getYI() <= maximum.getYI());
        Assert.assertTrue(v.getZI() <= maximum.getZI());
        Assert.assertTrue(v.getWI() <= maximum.getWI());
        Assert.assertTrue(v.getXI() >= minimum.getXI());
        Assert.assertTrue(v.getYI() >= minimum.getYI());
        Assert.assertTrue(v.getZI() >= minimum.getZI());
        Assert.assertTrue(v.getWI() >= minimum.getWI());
      }
    }
  }

  @Override @Test public void testClampMaximumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int maximum = VectorM4IContract.randomNegativeNumber();

      final int x = VectorM4IContract.randomPositiveNumber();
      final int y = VectorM4IContract.randomPositiveNumber();
      final int z = VectorM4IContract.randomPositiveNumber();
      final int w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      VectorM4I.clampMaximum(v, maximum, vr);

      Assert.assertTrue(vr.getXI() <= maximum);
      Assert.assertTrue(vr.getYI() <= maximum);
      Assert.assertTrue(vr.getZI() <= maximum);
      Assert.assertTrue(vr.getWI() <= maximum);

      {
        VectorM4I.clampMaximumInPlace(v, maximum);
        Assert.assertTrue(v.getXI() <= maximum);
        Assert.assertTrue(v.getYI() <= maximum);
        Assert.assertTrue(v.getZI() <= maximum);
        Assert.assertTrue(v.getWI() <= maximum);
      }
    }
  }

  @Override @Test public void testClampMinimumOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int minimum = VectorM4IContract.randomPositiveNumber();

      final int x = VectorM4IContract.randomNegativeNumber();
      final int y = VectorM4IContract.randomNegativeNumber();
      final int z = VectorM4IContract.randomNegativeNumber();
      final int w = VectorM4IContract.randomNegativeNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      VectorM4I.clampMinimum(v, minimum, vr);

      Assert.assertTrue(vr.getXI() >= minimum);
      Assert.assertTrue(vr.getYI() >= minimum);
      Assert.assertTrue(vr.getZI() >= minimum);
      Assert.assertTrue(vr.getWI() >= minimum);

      {
        VectorM4I.clampMinimumInPlace(v, minimum);
        Assert.assertTrue(v.getXI() >= minimum);
        Assert.assertTrue(v.getYI() >= minimum);
        Assert.assertTrue(v.getZI() >= minimum);
        Assert.assertTrue(v.getWI() >= minimum);
      }
    }
  }

  @Override @Test public void testClampOrdering()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int minimum = VectorM4IContract.randomNegativeNumber();
      final int maximum = VectorM4IContract.randomPositiveNumber();

      final int x = VectorM4IContract.randomNegativeNumber();
      final int y = VectorM4IContract.randomPositiveNumber();
      final int z = VectorM4IContract.randomPositiveNumber();
      final int w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();
      VectorM4I.clamp(v, minimum, maximum, vr);

      Assert.assertTrue(vr.getXI() <= maximum);
      Assert.assertTrue(vr.getXI() >= minimum);
      Assert.assertTrue(vr.getYI() <= maximum);
      Assert.assertTrue(vr.getYI() >= minimum);
      Assert.assertTrue(vr.getZI() <= maximum);
      Assert.assertTrue(vr.getZI() >= minimum);
      Assert.assertTrue(vr.getWI() <= maximum);
      Assert.assertTrue(vr.getWI() >= minimum);

      {
        VectorM4I.clampInPlace(v, minimum, maximum);

        Assert.assertTrue(v.getXI() <= maximum);
        Assert.assertTrue(v.getXI() >= minimum);
        Assert.assertTrue(v.getYI() <= maximum);
        Assert.assertTrue(v.getYI() >= minimum);
        Assert.assertTrue(v.getZI() <= maximum);
        Assert.assertTrue(v.getZI() >= minimum);
        Assert.assertTrue(v.getWI() <= maximum);
        Assert.assertTrue(v.getWI() >= minimum);
      }
    }
  }

  @Override @Test public void testCopy()
  {
    final VectorM4I vb = this.newVectorM4I(5, 6, 7, 8);
    final VectorM4I va = this.newVectorM4I(1, 2, 3, 4);

    Assert.assertFalse(va.getXI() == vb.getXI());
    Assert.assertFalse(va.getYI() == vb.getYI());
    Assert.assertFalse(va.getZI() == vb.getZI());
    Assert.assertFalse(va.getWI() == vb.getWI());

    VectorM4I.copy(va, vb);

    Assert.assertTrue(va.getXI() == vb.getXI());
    Assert.assertTrue(va.getYI() == vb.getYI());
    Assert.assertTrue(va.getZI() == vb.getZI());
    Assert.assertTrue(va.getWI() == vb.getWI());
  }

  @Override @Test public void testCopy2Correct()
  {
    final VectorM4I v0 = this.newVectorM4I(
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE);
    final VectorM4I v1 = this.newVectorM4I();

    v1.copyFrom2I(v0);

    Assert.assertEquals(v0.getXI(), v1.getXI());
    Assert.assertEquals(v0.getYI(), v1.getYI());
    Assert.assertEquals(0, v1.getZI());
    Assert.assertEquals(1, v1.getWI());
  }

  @Override @Test public void testCopy3Correct()
  {
    final VectorM4I v0 = this.newVectorM4I(
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE);
    final VectorM4I v1 = this.newVectorM4I();

    v1.copyFrom4I(v0);

    Assert.assertEquals(v0.getXI(), v1.getXI());
    Assert.assertEquals(v0.getYI(), v1.getYI());
    Assert.assertEquals(v0.getZI(), v1.getZI());
    Assert.assertEquals(v0.getWI(), v1.getWI());
  }

  @Override @Test public void testCopy4Correct()
  {
    final VectorM4I v0 = this.newVectorM4I(
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE,
      (int) Math.random() * Integer.MAX_VALUE);
    final VectorM4I v1 = this.newVectorM4I();

    v1.copyFrom3I(v0);

    Assert.assertEquals(v0.getXI(), v1.getXI());
    Assert.assertEquals(v0.getYI(), v1.getYI());
    Assert.assertEquals(v0.getZI(), v1.getZI());
    Assert.assertEquals(1, v1.getWI());
  }

  @Override @Test public void testDefault0001()
  {
    Assert.assertTrue(
      this.newVectorM4I()
        .equals(this.newVectorM4I(0, 0, 0, 1)));
  }

  @Override @Test public void testDistance()
  {
    final VectorM4I.Context4I c = new VectorM4I.Context4I();
    final VectorM4I v0 = this.newVectorM4I(0, 1, 0, 0);
    final VectorM4I v1 = this.newVectorM4I(0, 0, 0, 0);
    Assert.assertTrue(VectorM4I.distance(c, v0, v1) == 1);
  }

  @Override @Test public void testDistanceOrdering()
  {
    final VectorM4I.Context4I c = new VectorM4I.Context4I();
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x0 = VectorM4IContract.randomPositiveSmallNumber();
      final int y0 = VectorM4IContract.randomPositiveSmallNumber();
      final int z0 = VectorM4IContract.randomPositiveSmallNumber();
      final int w0 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v0 = this.newVectorM4I(x0, y0, z0, w0);

      final int x1 = VectorM4IContract.randomPositiveSmallNumber();
      final int y1 = VectorM4IContract.randomPositiveSmallNumber();
      final int z1 = VectorM4IContract.randomPositiveSmallNumber();
      final int w1 = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v1 = this.newVectorM4I(x1, y1, z1, w1);

      Assert.assertTrue(VectorM4I.distance(c, v0, v1) >= 0);
    }
  }

  @Override @Test public void testDotProduct()
  {
    final VectorM4I v0 = this.newVectorM4I(10, 10, 10, 10);
    final VectorM4I v1 = this.newVectorM4I(10, 10, 10, 10);

    {
      final int p = VectorM4I.dotProduct(v0, v1);
      Assert.assertTrue(v0.getXI() == 10);
      Assert.assertTrue(v0.getYI() == 10);
      Assert.assertTrue(v0.getZI() == 10);
      Assert.assertTrue(v0.getWI() == 10);
      Assert.assertTrue(v1.getXI() == 10);
      Assert.assertTrue(v1.getYI() == 10);
      Assert.assertTrue(v1.getZI() == 10);
      Assert.assertTrue(v1.getWI() == 10);
      Assert.assertTrue(p == 400);
    }

    {
      final int p = VectorM4I.dotProduct(v0, v0);
      Assert.assertTrue(v0.getXI() == 10);
      Assert.assertTrue(v0.getYI() == 10);
      Assert.assertTrue(v0.getZI() == 10);
      Assert.assertTrue(v0.getWI() == 10);
      Assert.assertTrue(p == 400);
    }

    {
      final int p = VectorM4I.dotProduct(v1, v1);
      Assert.assertTrue(v1.getXI() == 10);
      Assert.assertTrue(v1.getYI() == 10);
      Assert.assertTrue(v1.getZI() == 10);
      Assert.assertTrue(v1.getWI() == 10);
      Assert.assertTrue(p == 400);
    }
  }

  @Override @Test public void testDotProductPerpendicular()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int max = 1000;
      final int x = (int) (Math.random() * max);
      final int y = (int) (Math.random() * max);
      final int z = (int) (Math.random() * max);
      final int w = (int) (Math.random() * max);
      final VectorM4I q = this.newVectorM4I(x, y, z, w);

      final double ms = VectorM4I.magnitudeSquared(q);
      final double dp = VectorM4I.dotProduct(q, q);

      System.out.println("q  : " + q);
      System.out.println("ms : " + ms);
      System.out.println("dp : " + dp);

      AlmostEqualDouble.almostEqual(ec, ms, dp);
    }
  }

  @Override @Test public void testDotProductSelf()
  {
    final AlmostEqualDouble.ContextRelative ec =
      TestUtilities.getDoubleEqualityContext();

    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int max = 1000;
      final int x = (int) (Math.random() * max);
      final int y = (int) (Math.random() * max);
      final int z = (int) (Math.random() * max);
      final int w = (int) (Math.random() * max);
      final VectorM4I q = this.newVectorM4I(x, y, z, w);
      final double dp = VectorM4I.dotProduct(q, q);

      System.out.println("q  : " + q);
      System.out.println("dp : " + dp);

      AlmostEqualDouble.almostEqual(ec, 1.0, dp);
    }
  }

  @Override @Test public void testDotProductSelfMagnitudeSquared()
  {
    final VectorM4I v0 = this.newVectorM4I(10, 10, 10, 10);

    {
      final int p = VectorM4I.dotProduct(v0, v0);
      Assert.assertTrue(v0.getXI() == 10);
      Assert.assertTrue(v0.getYI() == 10);
      Assert.assertTrue(v0.getZI() == 10);
      Assert.assertTrue(v0.getWI() == 10);
      Assert.assertTrue(p == 400);
    }

    {
      final int p = VectorM4I.magnitudeSquared(v0);
      Assert.assertTrue(v0.getXI() == 10);
      Assert.assertTrue(v0.getYI() == 10);
      Assert.assertTrue(v0.getZI() == 10);
      Assert.assertTrue(v0.getWI() == 10);
      Assert.assertTrue(p == 400);
    }
  }

  @Override @Test public void testEqualsCorrect()
  {
    {
      final VectorM4I m0 = this.newVectorM4I();
      Assert.assertTrue(m0.equals(m0));
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      Assert.assertFalse(m0.equals(null));
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      Assert.assertFalse(m0.equals(Integer.valueOf(23)));
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      final VectorM4I m1 = this.newVectorM4I();
      Assert.assertTrue(m0.equals(m1));
    }
  }

  @Override @Test public void testEqualsNotEqualCorrect()
  {
    final int x = (int) (Math.random() * 1000);
    final int y = x + 1;
    final int z = y + 1;
    final int w = z + 1;
    final int q = w + 1;

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      Assert.assertFalse(m0.equals(null));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      Assert.assertFalse(m0.equals(Integer.valueOf(23)));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, y, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(x, q, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(x, y, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(x, y, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, q, z, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, y, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, y, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, q, q, w);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, q, z, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(q, q, q, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(x, q, q, q);
      Assert.assertFalse(m0.equals(m1));
    }

    {
      final VectorM4I m0 = this.newVectorM4I(x, y, z, w);
      final VectorM4I m1 = this.newVectorM4I(x, y, q, q);
      Assert.assertFalse(m0.equals(m1));
    }
  }

  @Override @Test public void testHashCodeEqualsCorrect()
  {
    final VectorM4I m0 = this.newVectorM4I();
    final VectorM4I m1 = this.newVectorM4I();
    Assert.assertEquals(m0.hashCode(), m1.hashCode());
  }

  @Override @Test public void testHashCodeNotEqualCorrect()
  {
    {
      final VectorM4I m0 = this.newVectorM4I();
      final VectorM4I m1 = this.newVectorM4I();
      m1.setXI(23);
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      final VectorM4I m1 = this.newVectorM4I();
      m1.setYI(23);
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      final VectorM4I m1 = this.newVectorM4I();
      m1.setZI(23);
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }

    {
      final VectorM4I m0 = this.newVectorM4I();
      final VectorM4I m1 = this.newVectorM4I();
      m1.setWI(23);
      Assert.assertFalse(m0.hashCode() == m1.hashCode());
    }
  }

  @Override @Test public void testInitializeReadable()
  {
    final VectorM4I v0 = this.newVectorM4I(1, 2, 3, 4);
    final VectorM4I v1 = new VectorM4I(v0);

    Assert.assertTrue(v0.getXI() == v1.getXI());
    Assert.assertTrue(v0.getYI() == v1.getYI());
    Assert.assertTrue(v0.getZI() == v1.getZI());
    Assert.assertTrue(v0.getWI() == v1.getWI());
  }

  @Override @Test public void testInterpolateLinearLimits()
  {
    final VectorM4I.Context4I c = new VectorM4I.Context4I();
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x0 = VectorM4IContract.randomPositiveNumber();
      final int y0 = VectorM4IContract.randomPositiveNumber();
      final int z0 = VectorM4IContract.randomPositiveNumber();
      final int w0 = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v0 = this.newVectorM4I(x0, y0, z0, w0);

      final int x1 = VectorM4IContract.randomPositiveNumber();
      final int y1 = VectorM4IContract.randomPositiveNumber();
      final int z1 = VectorM4IContract.randomPositiveNumber();
      final int w1 = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v1 = this.newVectorM4I(x1, y1, z1, w1);

      final VectorM4I vr0 = this.newVectorM4I();
      final VectorM4I vr1 = this.newVectorM4I();
      VectorM4I.interpolateLinear(c, v0, v1, 0, vr0);
      VectorM4I.interpolateLinear(c, v0, v1, 1, vr1);

      Assert.assertTrue(v0.getXI() == vr0.getXI());
      Assert.assertTrue(v0.getYI() == vr0.getYI());
      Assert.assertTrue(v0.getZI() == vr0.getZI());
      Assert.assertTrue(v0.getWI() == vr0.getWI());

      Assert.assertTrue(v1.getXI() == vr1.getXI());
      Assert.assertTrue(v1.getYI() == vr1.getYI());
      Assert.assertTrue(v1.getZI() == vr1.getZI());
      Assert.assertTrue(v1.getWI() == vr1.getWI());
    }
  }

  @Override @Test public void testMagnitudeNonzero()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x = VectorM4IContract.randomPositiveSmallNumber();
      final int y = VectorM4IContract.randomPositiveSmallNumber();
      final int z = VectorM4IContract.randomPositiveSmallNumber();
      final int w = VectorM4IContract.randomPositiveSmallNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final int m = VectorM4I.magnitude(v);
      Assert.assertTrue(m >= 1);
    }
  }

  @Override @Test public void testMagnitudeNormal()
  {
    // Not applicable to integer vectors
  }

  @Override @Test public void testMagnitudeNormalizeZero()
  {
    // Not applicable to integer vectors
  }

  @Override @Test public void testMagnitudeOne()
  {
    final VectorM4I v = this.newVectorM4I(1, 0, 0, 0);
    final int m = VectorM4I.magnitude(v);
    Assert.assertTrue(m == 1);
  }

  @Override @Test public void testMagnitudeSimple()
  {
    final VectorM4I v = this.newVectorM4I(8, 0, 0, 0);

    {
      final int p = VectorM4I.dotProduct(v, v);
      final int q = VectorM4I.magnitudeSquared(v);
      final int r = VectorM4I.magnitude(v);
      Assert.assertTrue(p == 64);
      Assert.assertTrue(q == 64);
      Assert.assertTrue(r == 8);
    }
  }

  @Override @Test public void testMagnitudeZero()
  {
    final VectorM4I v = this.newVectorM4I(0, 0, 0, 0);
    final int m = VectorM4I.magnitude(v);
    Assert.assertTrue(m == 0);
  }

  @Override @Test public void testNormalizeSimple()
  {
    // Not applicable to integer vectors
  }

  @Override @Test public void testNormalizeZero()
  {
    // Not supported by integer vectors
  }

  @Override @Test public void testOrthonormalize()
  {
    // Not applicable to integer vectors
  }

  @Override @Test public void testOrthonormalizeMutation()
  {
    // Not applicable to integer vectors
  }

  @Override @Test public void testProjectionPerpendicularZero()
  {
    {
      final VectorM4I p = this.newVectorM4I(1, 0, 0, 0);
      final VectorM4I q = this.newVectorM4I(0, 1, 0, 0);
      final VectorM4I r = this.newVectorM4I();
      final VectorM4I u = VectorM4I.projection(p, q, r);

      Assert.assertSame(r, u);
      Assert.assertTrue(VectorM4I.magnitude(u) == 0);
    }

    {
      final VectorM4I p = this.newVectorM4I(-1, 0, 0, 0);
      final VectorM4I q = this.newVectorM4I(0, 1, 0, 0);
      final VectorM4I r = this.newVectorM4I();
      final VectorM4I u = VectorM4I.projection(p, q, r);

      Assert.assertSame(r, u);
      Assert.assertTrue(VectorM4I.magnitude(u) == 0);
    }
  }

  @Override @Test public void testScaleMutation()
  {
    final VectorM4I out = this.newVectorM4I();
    final VectorM4I v0 = this.newVectorM4I(1, 1, 1, 1);

    Assert.assertTrue(out.getXI() == 0);
    Assert.assertTrue(out.getYI() == 0);
    Assert.assertTrue(out.getZI() == 0);
    Assert.assertTrue(out.getWI() == 1);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);

    final VectorM4I ov0 = VectorM4I.scale(v0, 2, out);

    Assert.assertTrue(out == ov0);
    Assert.assertTrue(out.getXI() == 2);
    Assert.assertTrue(out.getYI() == 2);
    Assert.assertTrue(out.getZI() == 2);
    Assert.assertTrue(out.getWI() == 2);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);

    final VectorM4I ov1 = VectorM4I.scaleInPlace(v0, 2);

    Assert.assertTrue(ov1 == v0);
    Assert.assertTrue(ov1.getXI() == 2);
    Assert.assertTrue(ov1.getYI() == 2);
    Assert.assertTrue(ov1.getZI() == 2);
    Assert.assertTrue(ov1.getWI() == 2);
    Assert.assertTrue(v0.getXI() == 2);
    Assert.assertTrue(v0.getYI() == 2);
    Assert.assertTrue(v0.getZI() == 2);
    Assert.assertTrue(v0.getWI() == 2);
  }

  @Override @Test public void testScaleOne()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x = VectorM4IContract.randomPositiveNumber();
      final int y = VectorM4IContract.randomPositiveNumber();
      final int z = VectorM4IContract.randomPositiveNumber();
      final int w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();

      VectorM4I.scale(v, 1, vr);

      Assert.assertTrue(v.getXI() == vr.getXI());
      Assert.assertTrue(v.getYI() == vr.getYI());
      Assert.assertTrue(v.getZI() == vr.getZI());
      Assert.assertTrue(v.getWI() == vr.getWI());

      {
        final int orig_x = v.getXI();
        final int orig_y = v.getYI();
        final int orig_z = v.getZI();
        final int orig_w = v.getWI();

        VectorM4I.scaleInPlace(v, 1);

        Assert.assertTrue(v.getXI() == orig_x);
        Assert.assertTrue(v.getYI() == orig_y);
        Assert.assertTrue(v.getZI() == orig_z);
        Assert.assertTrue(v.getWI() == orig_w);
      }
    }
  }

  @Override @Test public void testScaleZero()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x = VectorM4IContract.randomPositiveNumber();
      final int y = VectorM4IContract.randomPositiveNumber();
      final int z = VectorM4IContract.randomPositiveNumber();
      final int w = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v = this.newVectorM4I(x, y, z, w);

      final VectorM4I vr = this.newVectorM4I();

      VectorM4I.scale(v, 0, vr);

      Assert.assertTrue(vr.getXI() == 0);
      Assert.assertTrue(vr.getYI() == 0);
      Assert.assertTrue(vr.getZI() == 0);
      Assert.assertTrue(vr.getWI() == 0);

      {
        VectorM4I.scaleInPlace(v, 0);

        Assert.assertTrue(v.getXI() == 0);
        Assert.assertTrue(v.getYI() == 0);
        Assert.assertTrue(v.getZI() == 0);
        Assert.assertTrue(v.getWI() == 0);
      }
    }
  }

  @Override @Test public void testString()
  {
    final VectorM4I v = this.newVectorM4I(1, 2, 3, 4);
    Assert.assertTrue(v.toString().equals("[VectorM4I 1 2 3 4]"));
  }

  @Override @Test public void testSubtract()
  {
    for (int index = 0; index < TestUtilities.TEST_RANDOM_ITERATIONS; ++index) {
      final int x0 = VectorM4IContract.randomPositiveNumber();
      final int y0 = VectorM4IContract.randomPositiveNumber();
      final int z0 = VectorM4IContract.randomPositiveNumber();
      final int w0 = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v0 = this.newVectorM4I(x0, y0, z0, w0);

      final int x1 = VectorM4IContract.randomPositiveNumber();
      final int y1 = VectorM4IContract.randomPositiveNumber();
      final int z1 = VectorM4IContract.randomPositiveNumber();
      final int w1 = VectorM4IContract.randomPositiveNumber();
      final VectorM4I v1 = this.newVectorM4I(x1, y1, z1, w1);

      final VectorM4I vr0 = this.newVectorM4I();
      VectorM4I.subtract(v0, v1, vr0);

      Assert.assertTrue(vr0.getXI() == (v0.getXI() - v1.getXI()));
      Assert.assertTrue(vr0.getYI() == (v0.getYI() - v1.getYI()));
      Assert.assertTrue(vr0.getZI() == (v0.getZI() - v1.getZI()));
      Assert.assertTrue(vr0.getWI() == (v0.getWI() - v1.getWI()));

      {
        final int orig_x = v0.getXI();
        final int orig_y = v0.getYI();
        final int orig_z = v0.getZI();
        final int orig_w = v0.getWI();
        VectorM4I.subtractInPlace(v0, v1);

        Assert.assertTrue(v0.getXI() == (orig_x - v1.getXI()));
        Assert.assertTrue(v0.getYI() == (orig_y - v1.getYI()));
        Assert.assertTrue(v0.getZI() == (orig_z - v1.getZI()));
        Assert.assertTrue(v0.getWI() == (orig_w - v1.getWI()));
      }
    }
  }

  @Override @Test public void testSubtractMutation()
  {
    final VectorM4I out = this.newVectorM4I();
    final VectorM4I v0 = this.newVectorM4I(1, 1, 1, 1);
    final VectorM4I v1 = this.newVectorM4I(1, 1, 1, 1);

    Assert.assertTrue(out.getXI() == 0);
    Assert.assertTrue(out.getYI() == 0);
    Assert.assertTrue(out.getZI() == 0);
    Assert.assertTrue(out.getWI() == 1);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);

    final VectorM4I ov0 = VectorM4I.subtract(v0, v1, out);

    Assert.assertTrue(out == ov0);
    Assert.assertTrue(out.getXI() == 0);
    Assert.assertTrue(out.getYI() == 0);
    Assert.assertTrue(out.getZI() == 0);
    Assert.assertTrue(out.getWI() == 0);
    Assert.assertTrue(v0.getXI() == 1);
    Assert.assertTrue(v0.getYI() == 1);
    Assert.assertTrue(v0.getZI() == 1);
    Assert.assertTrue(v0.getWI() == 1);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);

    final VectorM4I ov1 = VectorM4I.subtractInPlace(v0, v1);

    Assert.assertTrue(ov1 == v0);
    Assert.assertTrue(ov1.getXI() == 0);
    Assert.assertTrue(ov1.getYI() == 0);
    Assert.assertTrue(ov1.getZI() == 0);
    Assert.assertTrue(ov1.getWI() == 0);
    Assert.assertTrue(v0.getXI() == 0);
    Assert.assertTrue(v0.getYI() == 0);
    Assert.assertTrue(v0.getZI() == 0);
    Assert.assertTrue(v0.getWI() == 0);
    Assert.assertTrue(v1.getXI() == 1);
    Assert.assertTrue(v1.getYI() == 1);
    Assert.assertTrue(v1.getZI() == 1);
    Assert.assertTrue(v1.getWI() == 1);
  }

  protected abstract VectorM4I newVectorM4I();
}
