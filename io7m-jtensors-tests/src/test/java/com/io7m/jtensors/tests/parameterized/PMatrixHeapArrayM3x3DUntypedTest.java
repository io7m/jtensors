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

import com.io7m.jtensors.MatrixM3x3D;
import com.io7m.jtensors.MatrixReadable3x3DType;
import com.io7m.jtensors.parameterized.PMatrix3x3DType;
import com.io7m.jtensors.parameterized.PMatrixHeapArrayM3x3D;
import com.io7m.jtensors.tests.Matrix3x3DContract;

public final class PMatrixHeapArrayM3x3DUntypedTest<T0, T1>
  extends Matrix3x3DContract<PMatrix3x3DType<T0, T1>>
{
  @Override
  protected PMatrix3x3DType<T0, T1> newMatrix()
  {
    return PMatrixHeapArrayM3x3D.newMatrix();
  }

  @Override
  protected PMatrix3x3DType<T0, T1> newMatrixFrom(
    final MatrixReadable3x3DType m)
  {
    final PMatrix3x3DType<T0, T1> r = PMatrixHeapArrayM3x3D.newMatrix();
    MatrixM3x3D.copy(m, r);
    return r;
  }

  @Override
  protected void checkDirectBufferInvariants(final PMatrix3x3DType<T0, T1> m)
  {
    // Nothing
  }
}