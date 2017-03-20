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

import com.io7m.jtensors.parameterized.PVectorM2D;
import com.io7m.jtensors.tests.VectorM2DContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PVectorM2DUntypedTest<T>
  extends VectorM2DContract<PVectorM2D<T>>
{
  private static final Logger LOG;

  static {
    LOG = LoggerFactory.getLogger(PVectorM2DUntypedTest.class);
  }

  @Override
  protected double delta()
  {
    return 0.0000000000001;
  }

  @Override
  protected double randomLargeNegative()
  {
    return Math.random() * -100000000.0;
  }

  @Override
  protected double randomLargePositive()
  {
    return Math.random() * 100000000.0;
  }

  @Override
  protected Logger logger()
  {
    return LOG;
  }

  @Override
  protected PVectorM2D<T> newVectorM2D(final PVectorM2D<T> v)
  {
    return new PVectorM2D<T>(v);
  }

  @Override
  protected PVectorM2D<T> newVectorM2D()
  {
    return new PVectorM2D<T>();
  }

  @Override
  protected PVectorM2D<T> newVectorM2D(
    final double x,
    final double y)
  {
    return new PVectorM2D<T>(x, y);
  }
}