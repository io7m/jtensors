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

package com.io7m.jtensors.generators.parameterized;

import com.io7m.jnull.NullCheck;
import com.io7m.jtensors.parameterized.PVectorM3I;
import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.support.IntegerGenerator;

/**
 * A vector generator.
 *
 * @param <T> A phantom type parameter
 */

public final class PVectorM3IGenerator<T> implements Generator<PVectorM3I<T>>
{
  private final Generator<Integer> gen;

  /**
   * Construct a generator.
   *
   * @param in_gen A scalar generator
   */

  public PVectorM3IGenerator(
    final Generator<Integer> in_gen)
  {
    this.gen = NullCheck.notNull(in_gen, "Generator");
  }

  /**
   * Construct a generator.
   */

  public PVectorM3IGenerator()
  {
    this.gen = new IntegerGenerator();
  }

  @Override
  public PVectorM3I<T> next()
  {
    return new PVectorM3I<T>(
      this.gen.next().intValue(),
      this.gen.next().intValue(),
      this.gen.next().intValue());
  }
}