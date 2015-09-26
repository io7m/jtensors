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

package com.io7m.jtensors.tests.bytebuffered.parameterized;

import com.io7m.jtensors.bytebuffered.parameterized.PVectorByteBufferedM2L;
import com.io7m.jtensors.parameterized.PVector2LType;
import com.io7m.jtensors.tests.parameterized.PVectorM2LBufferedContract;

import java.nio.ByteBuffer;

public final class PVectorByteBufferedM2LTest<T>
  extends PVectorM2LBufferedContract<T, PVector2LType<T>>
{
  @Override protected PVector2LType<T> newVectorM2L(
    final long x,
    final long y)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final PVector2LType<T> v =
      PVectorByteBufferedM2L.newVectorFromByteBuffer(buf, 50L);
    v.set2L(x, y);
    return v;
  }

  @Override protected PVector2LType<T> newVectorM2L()
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final PVector2LType<T> v =
      PVectorByteBufferedM2L.newVectorFromByteBuffer(buf, 50L);
    v.set2L(0L, 0L);
    return v;
  }

  @Override protected PVector2LType<T> newVectorM2L(
    final PVector2LType<T> v)
  {
    final ByteBuffer buf = ByteBuffer.allocate(100);
    final PVector2LType<T> vr =
      PVectorByteBufferedM2L.newVectorFromByteBuffer(buf, 50L);
    vr.copyFrom2L(v);
    return vr;
  }

  @Override protected PVector2LType<T> newVectorM2LAtIndexFromSize(
    final long size,
    final long offset)
  {
    final ByteBuffer buf = ByteBuffer.allocate((int) size);
    return PVectorByteBufferedM2L.newVectorFromByteBuffer(buf, offset);
  }
}
