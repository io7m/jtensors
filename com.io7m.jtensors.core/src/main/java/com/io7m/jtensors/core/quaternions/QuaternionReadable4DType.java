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

package com.io7m.jtensors.core.quaternions;

import org.immutables.value.Value;

/**
 * The type of 4D {@code double}-typed quaternions.
 */

public interface QuaternionReadable4DType
{
  /**
   * @return The {@code x} component
   */

  @Value.Parameter(order = 0)
  double x();

  /**
   * @return The {@code y} component
   */

  @Value.Parameter(order = 1)
  double y();

  /**
   * @return The {@code z} component
   */

  @Value.Parameter(order = 2)
  double z();

  /**
   * @return The {@code w} component
   */

  @Value.Parameter(order = 3)
  double w();
}
