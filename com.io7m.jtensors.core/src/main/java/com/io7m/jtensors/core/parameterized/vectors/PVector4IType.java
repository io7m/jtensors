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

package com.io7m.jtensors.core.parameterized.vectors;

import com.io7m.immutables.styles.ImmutablesStyleType;
import com.io7m.jtensors.core.VectorComputationalType;
import org.immutables.value.Value;

/**
 * The type of 4D {@code int}-typed vectors.
 *
 * @param <T> A phantom type parameter
 */

@ImmutablesStyleType
@Value.Immutable
public interface PVector4IType<T>
  extends PVectorReadable4IType<T>, VectorComputationalType
{
  @Override
  @Value.Parameter(order = 0)
  int x();

  @Override
  @Value.Parameter(order = 1)
  int y();

  @Override
  @Value.Parameter(order = 2)
  int z();

  @Override
  @Value.Parameter(order = 3)
  int w();
}
