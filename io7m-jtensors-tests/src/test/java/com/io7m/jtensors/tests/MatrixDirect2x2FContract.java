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

package com.io7m.jtensors.tests;

import com.io7m.jtensors.MatrixDirect2x2FType;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class MatrixDirect2x2FContract<T extends MatrixDirect2x2FType>
  extends Matrix2x2FContract<T>
{
  @Override
  protected abstract T newMatrix();

  @Test
  public final void testBufferEndianness()
  {
    final T m = this.newMatrix();
    final FloatBuffer b = m.getDirectFloatBuffer();

    Assert.assertEquals(ByteOrder.nativeOrder(), b.order());
  }

  @Test
  public void testStorage()
  {
    final T m = this.newMatrix();

    m.setR0C0F(0.0F);
    m.setR0C1F(1.0F);

    m.setR1C0F(100.0F);
    m.setR1C1F(101.0F);

    {
      final FloatBuffer b = m.getDirectFloatBuffer();

      Assert.assertEquals(ByteOrder.nativeOrder(), b.order());
      Assert.assertEquals(0L, (long) b.position());

      Assert.assertEquals(0.0, (double) b.get(0), 0.0);
      Assert.assertEquals(100.0, (double) b.get(1), 0.0);

      Assert.assertEquals(1.0, (double) b.get(2), 0.0);
      Assert.assertEquals(101.0, (double) b.get(3), 0.0);
    }
  }
}