/*
 * Copyright © 2017 Mark Raynsford <code@io7m.com> https://www.io7m.com
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

package com.io7m.jtensors.storage.api.parameterized.matrices;

import com.io7m.jtensors.core.parameterized.matrices.PMatrix2x2D;
import com.io7m.jtensors.core.parameterized.matrices.PMatrix2x2F;
import com.io7m.jtensors.core.parameterized.matrices.PMatrixReadable2x2DType;
import com.io7m.jtensors.storage.api.unparameterized.matrices.MatrixStorage2x2Type;

/**
 * The type of mutable 2x2 floating point number matrices.
 *
 * @param <A> A phantom type parameter (possibly representing a source
 *            coordinate system)
 * @param <B> A phantom type parameter (possibly representing a target
 *            coordinate system)
 */

public interface PMatrixStorage2x2Type<A, B>
  extends PMatrixReadable2x2DType<A, B>, MatrixStorage2x2Type
{
  /**
   * Set the components from the given matrix.
   *
   * @param m The source matrix
   */

  void setPMatrix2x2D(
    PMatrix2x2D<A, B> m);

  /**
   * Set the components from the given matrix.
   *
   * @param m The source matrix
   */

  void setPMatrix2x2F(
    PMatrix2x2F<A, B> m);
}
