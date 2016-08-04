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

package com.io7m.jtensors.tests.parameterized;

import com.io7m.jtensors.parameterized.PVectorM4I;
import com.io7m.jtensors.tests.VectorM4IContract;

public final class PVectorM4IUntypedTest<T>
  extends VectorM4IContract<PVectorM4I<T>>
{
  @Override protected PVectorM4I<T> newVectorM4I(
    final int x,
    final int y,
    final int z,
    final int w)
  {
    return new PVectorM4I<T>(x, y, z, w);
  }

  @Override protected PVectorM4I<T> newVectorM4I()
  {
    return new PVectorM4I<T>();
  }

  @Override protected PVectorM4I<T> newVectorM4IFrom(final PVectorM4I<T> v)
  {
    return new PVectorM4I<T>(v);
  }
}