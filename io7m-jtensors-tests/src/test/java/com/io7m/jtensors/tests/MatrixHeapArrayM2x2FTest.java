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

import com.io7m.jtensors.Matrix2x2FType;
import com.io7m.jtensors.MatrixHeapArrayM2x2F;
import com.io7m.jtensors.MatrixReadable2x2FType;

public final class MatrixHeapArrayM2x2FTest
  extends Matrix2x2FContract<Matrix2x2FType>
{
  @Override
  protected Matrix2x2FType newMatrix()
  {
    return MatrixHeapArrayM2x2F.newMatrix();
  }

  @Override
  protected Matrix2x2FType newMatrixFrom(
    final MatrixReadable2x2FType source)
  {
    return MatrixHeapArrayM2x2F.newMatrixFrom(source);
  }

  @Override
  protected void checkDirectBufferInvariants(final Matrix2x2FType m)
  {
    // Nothing required
  }
}