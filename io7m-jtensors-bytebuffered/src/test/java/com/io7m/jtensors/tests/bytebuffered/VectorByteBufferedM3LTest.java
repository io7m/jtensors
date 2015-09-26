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

import com.io7m.jtensors.Vector3LType;
import com.io7m.jtensors.bytebuffered.VectorByteBufferedM3L;
import com.io7m.jtensors.tests.VectorM3LBufferedContract;

import java.nio.ByteBuffer;

public final class VectorByteBufferedM3LTest
  extends VectorM3LBufferedContract<Vector3LType>
{
  @Override protected Vector3LType newVectorM3L(
    final long x,
    final long y,
    final long z)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector3LType v =
      VectorByteBufferedM3L.newVectorFromByteBuffer(buf, 50L);
    v.set3L(x, y, z);
    return v;
  }

  @Override protected Vector3LType newVectorM3L()
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector3LType v =
      VectorByteBufferedM3L.newVectorFromByteBuffer(buf, 50L);
    v.set3L(0L, 0L, 0L);
    return v;
  }

  @Override protected Vector3LType newVectorM3L(
    final Vector3LType v)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final Vector3LType vr =
      VectorByteBufferedM3L.newVectorFromByteBuffer(buf, 50L);
    vr.copyFrom3L(v);
    return vr;
  }

  @Override protected Vector3LType newVectorM3LAtIndexFromSize(
    final long size,
    final long offset)
  {
    final ByteBuffer buf = ByteBuffer.allocate((int) size);
    return VectorByteBufferedM3L.newVectorFromByteBuffer(buf, offset);
  }
}