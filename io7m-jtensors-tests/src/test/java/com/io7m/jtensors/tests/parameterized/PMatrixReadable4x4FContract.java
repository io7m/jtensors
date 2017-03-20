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

package com.io7m.jtensors.tests.parameterized;

import com.io7m.jtensors.Matrix4x4FType;
import com.io7m.jtensors.MatrixHeapArrayM4x4F;
import com.io7m.jtensors.VectorI4F;
import com.io7m.jtensors.VectorM2F;
import com.io7m.jtensors.VectorM3F;
import com.io7m.jtensors.VectorM4F;
import com.io7m.jtensors.VectorReadable4FType;
import com.io7m.jtensors.parameterized.PMatrixI4x4F;
import com.io7m.jtensors.parameterized.PMatrixReadable4x4FType;
import org.junit.Assert;
import org.junit.Test;

public abstract class PMatrixReadable4x4FContract<T0, T1, T extends
  PMatrixReadable4x4FType<T0, T1>>
{
  protected abstract T newMatrix();

  protected abstract T newMatrixFrom(
    PMatrixReadable4x4FType<T0, T1> source);

  protected abstract void checkDirectBufferInvariants(T m);

  @Test
  public final void testHashcodeNeqExhaustive()
  {
    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final T m0 = this.newMatrix();
        final Matrix4x4FType m1 = MatrixHeapArrayM4x4F.newMatrix();
        this.checkDirectBufferInvariants(m0);
        Assert.assertEquals(
          (double) m1.hashCode(), (double) m0.hashCode(), 0.0);
        this.checkDirectBufferInvariants(m0);
        m1.setRowColumnF(row, col, 256.0f);
        this.checkDirectBufferInvariants(m0);
        Assert.assertFalse(m0.hashCode() == m1.hashCode());
        this.checkDirectBufferInvariants(m0);
      }
    }
  }

  /**
   * Test that single-element retrievals are correct.
   */

  @Test
  public final void testGetCorrect()
  {
    final VectorReadable4FType c0 = new VectorI4F(
      1.0f, 10.0f, 100.0f, 1000.0f);
    final VectorReadable4FType c1 = new VectorI4F(
      2.0f, 20.0f, 200.0f, 2000.0f);
    final VectorReadable4FType c2 = new VectorI4F(
      3.0f, 30.0f, 300.0f, 3000.0f);
    final VectorReadable4FType c3 = new VectorI4F(
      4.0f, 40.0f, 400.0f, 4000.0f);
    final PMatrixI4x4F<T0, T1> m0 = PMatrixI4x4F.newFromColumns(c0, c1, c2, c3);

    final T mr = this.newMatrixFrom(m0);
    Assert.assertEquals(1.0, (double) mr.getR0C0F(), 0.0);
    Assert.assertEquals(10.0, (double) mr.getR1C0F(), 0.0);
    Assert.assertEquals(100.0, (double) mr.getR2C0F(), 0.0);
    Assert.assertEquals(1000.0, (double) mr.getR3C0F(), 0.0);

    Assert.assertEquals(2.0, (double) mr.getR0C1F(), 0.0);
    Assert.assertEquals(20.0, (double) mr.getR1C1F(), 0.0);
    Assert.assertEquals(200.0, (double) mr.getR2C1F(), 0.0);
    Assert.assertEquals(2000.0, (double) mr.getR3C1F(), 0.0);

    Assert.assertEquals(3.0, (double) mr.getR0C2F(), 0.0);
    Assert.assertEquals(30.0, (double) mr.getR1C2F(), 0.0);
    Assert.assertEquals(300.0, (double) mr.getR2C2F(), 0.0);
    Assert.assertEquals(3000.0, (double) mr.getR3C2F(), 0.0);

    Assert.assertEquals(4.0, (double) mr.getR0C3F(), 0.0);
    Assert.assertEquals(40.0, (double) mr.getR1C3F(), 0.0);
    Assert.assertEquals(400.0, (double) mr.getR2C3F(), 0.0);
    Assert.assertEquals(4000.0, (double) mr.getR3C3F(), 0.0);

    Assert.assertEquals(1.0, (double) mr.getRowColumnF(0, 0), 0.0);
    Assert.assertEquals(10.0, (double) mr.getRowColumnF(1, 0), 0.0);
    Assert.assertEquals(100.0, (double) mr.getRowColumnF(2, 0), 0.0);
    Assert.assertEquals(1000.0, (double) mr.getRowColumnF(3, 0), 0.0);

    Assert.assertEquals(2.0, (double) mr.getRowColumnF(0, 1), 0.0);
    Assert.assertEquals(20.0, (double) mr.getRowColumnF(1, 1), 0.0);
    Assert.assertEquals(200.0, (double) mr.getRowColumnF(2, 1), 0.0);
    Assert.assertEquals(2000.0, (double) mr.getRowColumnF(3, 1), 0.0);

    Assert.assertEquals(3.0, (double) mr.getRowColumnF(0, 2), 0.0);
    Assert.assertEquals(30.0, (double) mr.getRowColumnF(1, 2), 0.0);
    Assert.assertEquals(300.0, (double) mr.getRowColumnF(2, 2), 0.0);
    Assert.assertEquals(3000.0, (double) mr.getRowColumnF(3, 2), 0.0);

    Assert.assertEquals(4.0, (double) mr.getRowColumnF(0, 3), 0.0);
    Assert.assertEquals(40.0, (double) mr.getRowColumnF(1, 3), 0.0);
    Assert.assertEquals(400.0, (double) mr.getRowColumnF(2, 3), 0.0);
    Assert.assertEquals(4000.0, (double) mr.getRowColumnF(3, 3), 0.0);
  }

  /**
   * Test that row retrievals are correct.
   */

  @Test
  public final void testGetRow4Correct()
  {
    final VectorReadable4FType c0 = new VectorI4F(
      1.0f, 10.0f, 100.0f, 1000.0f);
    final VectorReadable4FType c1 = new VectorI4F(
      2.0f, 20.0f, 200.0f, 2000.0f);
    final VectorReadable4FType c2 = new VectorI4F(
      3.0f, 30.0f, 300.0f, 3000.0f);
    final VectorReadable4FType c3 = new VectorI4F(
      4.0f, 40.0f, 400.0f, 4000.0f);
    final PMatrixI4x4F<T0, T1> m0 = PMatrixI4x4F.newFromColumns(c0, c1, c2, c3);

    final T mr = this.newMatrixFrom(m0);

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4F(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(4.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4F(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(30.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(40.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4F(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(300.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(400.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4F(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3000.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(4000.0, (double) out.getWF(), 0.0);
    }

    /*
     * Unsafe
     */

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4FUnsafe(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(4.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4FUnsafe(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(30.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(40.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4FUnsafe(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(300.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(400.0, (double) out.getWF(), 0.0);
    }

    {
      final VectorM4F out = new VectorM4F();
      mr.getRow4FUnsafe(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3000.0, (double) out.getZF(), 0.0);
      Assert.assertEquals(4000.0, (double) out.getWF(), 0.0);
    }
  }

  /**
   * Test that row retrievals are correct.
   */

  @Test
  public final void testGetRow3Correct()
  {
    final VectorReadable4FType c0 = new VectorI4F(
      1.0f, 10.0f, 100.0f, 1000.0f);
    final VectorReadable4FType c1 = new VectorI4F(
      2.0f, 20.0f, 200.0f, 2000.0f);
    final VectorReadable4FType c2 = new VectorI4F(
      3.0f, 30.0f, 300.0f, 3000.0f);
    final VectorReadable4FType c3 = new VectorI4F(
      4.0f, 40.0f, 400.0f, 4000.0f);
    final PMatrixI4x4F<T0, T1> m0 = PMatrixI4x4F.newFromColumns(c0, c1, c2, c3);

    final T mr = this.newMatrixFrom(m0);

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3F(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3F(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(30.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3F(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(300.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3F(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3000.0, (double) out.getZF(), 0.0);
    }

    /*
     * Unsafe
     */

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3FUnsafe(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3FUnsafe(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(30.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3FUnsafe(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(300.0, (double) out.getZF(), 0.0);
    }

    {
      final VectorM3F out = new VectorM3F();
      mr.getRow3FUnsafe(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
      Assert.assertEquals(3000.0, (double) out.getZF(), 0.0);
    }
  }

  /**
   * Test that row retrievals are correct.
   */

  @Test
  public final void testGetRow2Correct()
  {
    final VectorReadable4FType c0 = new VectorI4F(
      1.0f, 10.0f, 100.0f, 1000.0f);
    final VectorReadable4FType c1 = new VectorI4F(
      2.0f, 20.0f, 200.0f, 2000.0f);
    final VectorReadable4FType c2 = new VectorI4F(
      3.0f, 30.0f, 300.0f, 3000.0f);
    final VectorReadable4FType c3 = new VectorI4F(
      4.0f, 40.0f, 400.0f, 4000.0f);
    final PMatrixI4x4F<T0, T1> m0 = PMatrixI4x4F.newFromColumns(c0, c1, c2, c3);

    final T mr = this.newMatrixFrom(m0);

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2F(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2F(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2F(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2F(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
    }

    /*
     * Unsafe
     */

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2FUnsafe(0, out);
      Assert.assertEquals(1.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2FUnsafe(1, out);
      Assert.assertEquals(10.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(20.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2FUnsafe(2, out);
      Assert.assertEquals(100.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(200.0, (double) out.getYF(), 0.0);
    }

    {
      final VectorM2F out = new VectorM2F();
      mr.getRow2FUnsafe(3, out);
      Assert.assertEquals(1000.0, (double) out.getXF(), 0.0);
      Assert.assertEquals(2000.0, (double) out.getYF(), 0.0);
    }
  }

  @Test
  public final void testString()
  {
    final T m0 = this.newMatrix();
    final T m1 = this.newMatrix();

    final Matrix4x4FType m2 = MatrixHeapArrayM4x4F.newMatrix();
    m2.setR0C0F(2.0f);

    Assert.assertEquals(m0.toString(), m1.toString());
    Assert.assertFalse(m0.toString().equals(m2.toString()));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public final void testGetOutOfBoundsColumn0()
  {
    final T m0 = this.newMatrix();
    m0.getRowColumnF(0, 4);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public final void testGetOutOfBoundsColumn1()
  {
    final T m0 = this.newMatrix();
    m0.getRowColumnF(0, -1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public final void testGetOutOfBoundsRow0()
  {
    final T m0 = this.newMatrix();
    m0.getRowColumnF(4, 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public final void testGetOutOfBoundsRow1()
  {
    final T m0 = this.newMatrix();
    m0.getRowColumnF(-1, 0);
  }
}