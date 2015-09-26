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

import com.io7m.jtensors.Matrix3x3DType;
import com.io7m.jtensors.MatrixM3x3D;
import com.io7m.jtensors.MatrixReadable3x3DType;
import com.io7m.jtensors.bytebuffered.MatrixByteBufferedM3x3D;
import com.io7m.jtensors.tests.Matrix3x3DBufferedContract;

import java.nio.ByteBuffer;

public final class MatrixByteBufferedM3x3DTest
  extends Matrix3x3DBufferedContract<Matrix3x3DType>
{
  @Override protected Matrix3x3DType newMatrix()
  {
    final ByteBuffer buf = ByteBuffer.allocate(200);
    final Matrix3x3DType mr =
      MatrixByteBufferedM3x3D.newMatrixFromByteBuffer(buf, 0L);
    MatrixM3x3D.setIdentity(mr);
    return mr;
  }

  @Override
  protected Matrix3x3DType newMatrixFrom(final MatrixReadable3x3DType m)
  {
    final ByteBuffer buf = ByteBuffer.allocate(200);
    final Matrix3x3DType mr =
      MatrixByteBufferedM3x3D.newMatrixFromByteBuffer(buf, 0L);
    MatrixM3x3D.copy(m, mr);
    return mr;
  }

  @Override protected void checkDirectBufferInvariants(final Matrix3x3DType m)
  {
    // Nothing required
  }

  @Override protected Matrix3x3DType newMatrixAtIndexFromSize(
    final long size,
    final long offset)
  {
    final ByteBuffer buf = ByteBuffer.allocate((int) size);
    final Matrix3x3DType mr =
      MatrixByteBufferedM3x3D.newMatrixFromByteBuffer(buf, offset);
    return mr;
  }
}