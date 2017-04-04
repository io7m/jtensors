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

package com.io7m.jtensors.tests.storage.bytebuffered;

import com.io7m.mutable.numbers.core.MutableLong;
import com.io7m.jtensors.core.unparameterized.vectors.Vector4L;
import com.io7m.jtensors.storage.bytebuffered.VectorByteBufferedIntegral4Type;
import com.io7m.jtensors.tests.storage.api.VectorStorageIntegral4Contract;
import org.junit.Test;

public abstract class VectorByteBufferedIntegral4Contract
  extends VectorStorageIntegral4Contract
{
  protected abstract VectorByteBufferedIntegral4Type create(
    final MutableLong base,
    final int offset);

  @Test
  public final void testGetSetRange()
  {
    final MutableLong base = MutableLong.create();
    final VectorByteBufferedIntegral4Type m = this.create(base, 0);

    m.setVector4L(Vector4L.of(0L, 1L, 2L, 3L));

    {
      this.checkEquals(0L, m.x());
      this.checkEquals(1L, m.y());
      this.checkEquals(2L, m.z());
      this.checkEquals(3L, m.w());
    }

    base.setValue((long) m.sizeBytes());

    {
      this.checkEquals(0L, m.x());
      this.checkEquals(0L, m.y());
      this.checkEquals(0L, m.z());
      this.checkEquals(0L, m.w());
    }

    m.setVector4L(Vector4L.of(0L, 1L, 2L, 3L));

    {
      this.checkEquals(0L, m.x());
      this.checkEquals(1L, m.y());
      this.checkEquals(2L, m.z());
      this.checkEquals(3L, m.w());
    }

    base.setValue(0L);

    {
      this.checkEquals(0L, m.x());
      this.checkEquals(1L, m.y());
      this.checkEquals(2L, m.z());
      this.checkEquals(3L, m.w());
    }
  }
}
