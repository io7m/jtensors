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

package com.io7m.jtensors.tests.orthonormalization;

import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jtensors.core.unparameterized.vectors.Vector4D;
import com.io7m.jtensors.core.unparameterized.vectors.Vectors4D;
import com.io7m.jtensors.generators.Vector4DGenerator;
import com.io7m.jtensors.orthonormalization.Orthonormalization;
import com.io7m.jtensors.orthonormalization.Orthonormalized4D;
import com.io7m.percentpass.extension.PercentPassing;
import net.java.quickcheck.Generator;
import org.junit.jupiter.api.Assertions;

/**
 * Test orthonormalization.
 */

public final class Orthonormalization4DTest
{
  private static Generator<Vector4D> createGeneratorSmall()
  {
    return Vector4DGenerator.createSmall();
  }

  @PercentPassing
  public void testOrtho4D()
  {
    final AlmostEqualDouble.ContextRelative ec =
      new AlmostEqualDouble.ContextRelative();
    ec.setMaxAbsoluteDifference(0.000001);
    ec.setMaxRelativeDifference(0.000001);

    final Generator<Vector4D> gen = createGeneratorSmall();
    final Vector4D v0 = gen.next();
    final Vector4D v1 = gen.next();
    final Vector4D v2 = gen.next();

    final Orthonormalized4D o =
      Orthonormalization.orthonormalize4D(v0, v1, v2);

    final double v0m = Vectors4D.magnitude(o.v0());
    final double v1m = Vectors4D.magnitude(o.v1());
    final double v2m = Vectors4D.magnitude(o.v2());

    final double v0_dot_v1 = Vectors4D.dotProduct(o.v0(), o.v1());
    final double v0_dot_v2 = Vectors4D.dotProduct(o.v0(), o.v2());
    final double v1_dot_v2 = Vectors4D.dotProduct(o.v1(), o.v2());

    System.err.println("v0              : " + v0);
    System.err.println("v1              : " + v1);
    System.err.println("v2              : " + v2);
    System.err.println("o.v0            : " + o.v0());
    System.err.println("o.v1            : " + o.v1());
    System.err.println("o.v2            : " + o.v2());
    System.err.println("magniture(o.v0) : " + v0m);
    System.err.println("magniture(o.v1) : " + v1m);
    System.err.println("magniture(o.v2) : " + v2m);
    System.err.println("o.v0 dot o.v1   : " + v0_dot_v1);
    System.err.println("o.v0 dot o.v2   : " + v0_dot_v2);
    System.err.println("o.v1 dot o.v2   : " + v1_dot_v2);
    System.err.println("--");

    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v0m, 1.0));
    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v1m, 1.0));
    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v2m, 1.0));

    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v0_dot_v1, 0.0));
    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v0_dot_v2, 0.0));
    Assertions.assertTrue(AlmostEqualDouble.almostEqual(ec, v1_dot_v2, 0.0));
  }
}
