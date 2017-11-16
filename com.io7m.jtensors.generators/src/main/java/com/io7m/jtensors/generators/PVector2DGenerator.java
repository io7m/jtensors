/*
 * Copyright © 2017 <code@io7m.com> http://io7m.com
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

package com.io7m.jtensors.generators;

import java.util.Objects;
import com.io7m.jtensors.core.parameterized.vectors.PVector2D;
import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;

/**
 * A vector generator.
 *
 * @param <T>
 */

public final class PVector2DGenerator<T> implements Generator<PVector2D<T>>
{
  private final Generator<Double> gen;

  /**
   * Construct a generator.
   *
   * @param in_gen A component generator
   */

  public PVector2DGenerator(
    final Generator<Double> in_gen)
  {
    this.gen = Objects.requireNonNull(in_gen, "Generator");
  }

  /**
   * Create a generator initialized with a default component generator that
   * produces values in the range {@code [-(2 ^ 16), 2 ^ 16]}.
   *
   * @param <T> A phantom type parameter
   *
   * @return A generator
   */

  public static <T> Generator<PVector2D<T>> createSmall()
  {
    return new PVector2DGenerator<>(PrimitiveGenerators.doubles(
      GeneratorConstants.BOUND_SMALL_DOUBLE_LOWER,
      GeneratorConstants.BOUND_SMALL_DOUBLE_UPPER
    ));
  }

  /**
   * Create a generator initialized with a default component generator.
   *
   * @param <T> A phantom type parameter
   *
   * @return A generator
   */

  public static <T> Generator<PVector2D<T>> create()
  {
    return new PVector2DGenerator<>(PrimitiveGenerators.doubles(
      GeneratorConstants.BOUND_LARGE_DOUBLE_LOWER,
      GeneratorConstants.BOUND_LARGE_DOUBLE_UPPER
    ));
  }

  /**
   * Create a generator initialized with a default component generator that
   * produces values in the range {@code [-1, 1]}.
   *
   * @param <T> A phantom type parameter
   *
   * @return A generator
   */

  public static <T> Generator<PVector2D<T>> createNormal()
  {
    return new PVector2DGenerator<>(PrimitiveGenerators.doubles(
      GeneratorConstants.BOUND_NORMAL_DOUBLE_LOWER,
      GeneratorConstants.BOUND_NORMAL_DOUBLE_UPPER
    ));
  }

  @Override
  public PVector2D<T> next()
  {
    return PVector2D.of(
      this.gen.next().doubleValue(),
      this.gen.next().doubleValue());
  }
}
