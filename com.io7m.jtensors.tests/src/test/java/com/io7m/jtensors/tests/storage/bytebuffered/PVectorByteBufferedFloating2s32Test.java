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

package com.io7m.jtensors.tests.storage.bytebuffered;

import com.io7m.jtensors.core.parameterized.vectors.PVector2D;
import com.io7m.jtensors.core.parameterized.vectors.PVector2F;
import com.io7m.jtensors.generators.PVector2DGenerator;
import com.io7m.jtensors.generators.PVector2FGenerator;
import com.io7m.jtensors.storage.api.parameterized.vectors.PVectorStorageFloating2Type;
import com.io7m.jtensors.storage.bytebuffered.PVectorByteBufferedFloating2Type;
import com.io7m.jtensors.storage.bytebuffered.PVectorByteBufferedFloating2s32;
import com.io7m.jtensors.tests.core.TestFOps;
import com.io7m.mutable.numbers.core.MutableLong;
import net.java.quickcheck.Generator;

import java.nio.ByteBuffer;

public final class PVectorByteBufferedFloating2s32Test
  extends PVectorByteBufferedFloating2Contract
{

  @Override
  protected PVectorStorageFloating2Type<Object> create(
    final int offset)
  {
    return this.create(MutableLong.create(), offset);
  }

  @Override
  protected PVectorByteBufferedFloating2Type<Object> create(
    final MutableLong base,
    final int offset)
  {
    return PVectorByteBufferedFloating2s32.createWithBase(
      ByteBuffer.allocate(BufferSizes.BUFFER_SIZE_DEFAULT),
      base,
      offset);
  }

  @Override
  protected Generator<PVector2D<Object>> createGenerator2D()
  {
    return PVector2DGenerator.createNormal();
  }

  @Override
  protected Generator<PVector2F<Object>> createGenerator2F()
  {
    return PVector2FGenerator.createNormal();
  }

  @Override
  protected void checkAlmostEquals(
    final double x,
    final double y)
  {
    TestFOps.checkAlmostEquals(x, y);
  }
}
