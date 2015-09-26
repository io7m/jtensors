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

package com.io7m.jtensors.tests.bytebuffered;

import com.io7m.jtensors.Vector2DType;
import com.io7m.jtensors.bytebuffered.VectorByteBufferedM2D;
import com.io7m.jtensors.tests.VectorM2DBufferedContract;

import java.nio.ByteBuffer;

public final class VectorByteBufferedM2DTest
  extends VectorM2DBufferedContract<Vector2DType>
{
  @Override protected Vector2DType newVectorM2D(
    final double x,
    final double y)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector2DType v =
      VectorByteBufferedM2D.newVectorFromByteBuffer(buf, 50L);
    v.set2D(x, y);
    return v;
  }

  @Override protected Vector2DType newVectorM2D()
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector2DType v =
      VectorByteBufferedM2D.newVectorFromByteBuffer(buf, 50L);
    v.set2D(0.0, 0.0);
    return v;
  }

  @Override protected Vector2DType newVectorM2D(
    final Vector2DType v)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector2DType vr =
      VectorByteBufferedM2D.newVectorFromByteBuffer(buf, 50L);
    vr.copyFrom2D(v);
    return vr;
  }

  @Override protected Vector2DType newVectorM2DAtIndexFromSize(
    final long size,
    final long offset)
  {
    final ByteBuffer buf = ByteBuffer.allocate((int) size);
    return VectorByteBufferedM2D.newVectorFromByteBuffer(buf, offset);
  }
}