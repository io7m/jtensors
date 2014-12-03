/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
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

import com.io7m.jtensors.MatrixI3x3D;
import com.io7m.jtensors.MatrixM3x3D;
import com.io7m.jtensors.VectorI3D;
import com.io7m.jtensors.VectorM3D;

@SuppressWarnings("static-method") public class MatrixI3x3DTest
{
  @Test public void testEquals()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();

    int index = 0;
    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        m0.set(row, col, index);
        ++index;
      }
    }

    final MatrixI3x3D im0 = MatrixI3x3D.newFromReadable(m0);
    final MatrixI3x3D im1 = MatrixI3x3D.newFromReadable(m0);

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        Assert.assertEquals(
          im0.getRowColumnD(row, col),
          m0.getRowColumnD(row, col),
          0.0);
      }
    }

    Assert.assertEquals(im0, im0);
    Assert.assertEquals(im0.hashCode(), im0.hashCode());
    Assert.assertEquals(im0, im1);
    Assert.assertFalse(im0.equals(null));
    Assert.assertFalse(im0.equals(Integer.valueOf(23)));

    index = 100;
    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        m0.set(row, col, index);
        ++index;
      }
    }

    final MatrixI3x3D im2 = MatrixI3x3D.newFromReadable(m0);
    Assert.assertFalse(im0.equals(im2));
  }

  @Test public void testFromColumns()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();

    m0.set(0, 0, 0.0f);
    m0.set(1, 0, 0.1f);
    m0.set(2, 0, 0.2f);

    m0.set(0, 1, 10.0f);
    m0.set(1, 1, 10.1f);
    m0.set(2, 1, 10.2f);

    m0.set(0, 2, 20.0f);
    m0.set(1, 2, 20.1f);
    m0.set(2, 2, 20.2f);

    final MatrixI3x3D im0 = MatrixI3x3D.newFromReadable(m0);

    final VectorI3D column_0 = new VectorI3D(0.0f, 0.1f, 0.2f);
    final VectorI3D column_1 = new VectorI3D(10.0f, 10.1f, 10.2f);
    final VectorI3D column_2 = new VectorI3D(20.0f, 20.1f, 20.2f);

    final MatrixI3x3D im1 =
      MatrixI3x3D.newFromColumns(column_0, column_1, column_2);

    Assert.assertEquals(im0, im1);
  }

  @Test public void testFromRows()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();

    m0.set(0, 0, 0.0f);
    m0.set(1, 0, 0.1f);
    m0.set(2, 0, 0.2f);

    m0.set(0, 1, 10.0f);
    m0.set(1, 1, 10.1f);
    m0.set(2, 1, 10.2f);

    m0.set(0, 2, 20.0f);
    m0.set(1, 2, 20.1f);
    m0.set(2, 2, 20.2f);

    final MatrixI3x3D im = MatrixI3x3D.newFromReadable(m0);

    final VectorM3D row = new VectorM3D();

    im.getRow3D(0, row);
    Assert.assertEquals(0.0f, row.getXD(), 0.0);
    Assert.assertEquals(10.0f, row.getYD(), 0.0);
    Assert.assertEquals(20.0f, row.getZD(), 0.0);

    im.getRow3D(1, row);
    Assert.assertEquals(0.1f, row.getXD(), 0.0);
    Assert.assertEquals(10.1f, row.getYD(), 0.0);
    Assert.assertEquals(20.1f, row.getZD(), 0.0);

    im.getRow3D(2, row);
    Assert.assertEquals(0.2f, row.getXD(), 0.0);
    Assert.assertEquals(10.2f, row.getYD(), 0.0);
    Assert.assertEquals(20.2f, row.getZD(), 0.0);
  }

  @Test public void testIdentity()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();
    final MatrixI3x3D im0 = MatrixI3x3D.identity();
    final MatrixI3x3D im1 = MatrixI3x3D.newFromReadable(m0);
    Assert.assertEquals(im1, im0);
  }

  @Test public void testMakeMatrix3x3D()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();
    final MatrixM3x3D m1 = new MatrixM3x3D();

    int index = 0;
    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        m0.set(row, col, index);
        ++index;
      }
    }

    final MatrixI3x3D im = MatrixI3x3D.newFromReadable(m0);
    im.makeMatrixM3x3D(m1);
    Assert.assertEquals(m0, m1);
  }

  @Test public void testToString()
  {
    final MatrixM3x3D m0 = new MatrixM3x3D();
    final MatrixM3x3D m1 = new MatrixM3x3D();

    int index = 0;
    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        m0.set(row, col, index);
        m1.set(row, col, index);
        ++index;
      }
    }

    final MatrixI3x3D im0 = MatrixI3x3D.newFromReadable(m0);
    final MatrixI3x3D im1 = MatrixI3x3D.newFromReadable(m1);
    Assert.assertEquals(im1.toString(), im0.toString());
  }
}