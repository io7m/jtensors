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

package com.io7m.jtensors.bytebuffered.parameterized;

import com.io7m.jintegers.CheckedMath;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;
import com.io7m.jtensors.VectorReadable2LType;
import com.io7m.jtensors.VectorReadable3LType;
import com.io7m.jtensors.parameterized.PVector3LType;
import com.io7m.jtensors.parameterized.PVectorReadable2LType;
import com.io7m.jtensors.parameterized.PVectorReadable3LType;

import java.nio.ByteBuffer;

/**
 * <p>A three-element vector type with {@code long} elements, packed into a
 * {@link ByteBuffer}.</p>
 *
 * <p> Values of this type cannot be accessed safely from multiple threads
 * without explicit synchronization. </p>
 *
 * @param <T> A phantom type parameter
 */

public final class PVectorByteBufferedM3L<T> implements PVector3LType<T>
{
  private final ByteBuffer buffer;
  private final long       offset;

  private PVectorByteBufferedM3L(
    final ByteBuffer in_buffer,
    final long in_offset)
  {
    this.buffer = NullCheck.notNull(in_buffer);
    this.offset = in_offset;
  }

  /**
   * <p>Return a new vector that is backed by whatever data is at byte offset
   * {@code byte_offset} in the byte buffer {@code}.</p>
   *
   * <p>No initialization of the data is performed.</p>
   *
   * @param <T>         A phantom type parameter
   * @param b           The byte buffer
   * @param byte_offset A byte offset
   *
   * @return A new buffered vector
   */

  public static <T> PVector3LType<T> newVectorFromByteBuffer(
    final ByteBuffer b,
    final long byte_offset)
  {
    return new PVectorByteBufferedM3L<T>(b, byte_offset);
  }

  private static int getByteOffsetForIndex(
    final long base,
    final int index)
  {
    final long b = CheckedMath.add(base, (long) (index * 8));
    if (b >= (long) Integer.MAX_VALUE) {
      throw new IndexOutOfBoundsException(Long.toString(b));
    }
    return (int) b;
  }

  @Override public long getZL()
  {
    return this.getAtOffsetAndIndex(this.offset, 2);
  }

  @Override public void setZL(final long z)
  {
    this.setAtOffsetAndIndex(this.offset, 2, z);
  }

  @Override public long getXL()
  {
    return this.getAtOffsetAndIndex(this.offset, 0);
  }

  @Override public void setXL(final long x)
  {
    this.setAtOffsetAndIndex(this.offset, 0, x);
  }

  private void setAtOffsetAndIndex(
    final long o,
    final int i,
    final long x)
  {
    this.buffer.putLong(PVectorByteBufferedM3L.getByteOffsetForIndex(o, i), x);
  }

  private long getAtOffsetAndIndex(
    final long o,
    final int i)
  {
    return this.buffer.getLong(
      PVectorByteBufferedM3L.getByteOffsetForIndex(o, i));
  }

  @Override public long getYL()
  {
    return this.getAtOffsetAndIndex(this.offset, 1);
  }

  @Override public void setYL(final long y)
  {
    this.setAtOffsetAndIndex(this.offset, 1, y);
  }

  @Override public void copyFrom3L(final VectorReadable3LType in_v)
  {
    this.setAtOffsetAndIndex(this.offset, 0, in_v.getXL());
    this.setAtOffsetAndIndex(this.offset, 1, in_v.getYL());
    this.setAtOffsetAndIndex(this.offset, 2, in_v.getZL());
  }

  @Override public void set3L(
    final long x,
    final long y,
    final long z)
  {
    this.setAtOffsetAndIndex(this.offset, 0, x);
    this.setAtOffsetAndIndex(this.offset, 1, y);
    this.setAtOffsetAndIndex(this.offset, 2, z);
  }

  @Override public void copyFrom2L(final VectorReadable2LType in_v)
  {
    this.setAtOffsetAndIndex(this.offset, 0, in_v.getXL());
    this.setAtOffsetAndIndex(this.offset, 1, in_v.getYL());
  }

  @Override public void set2L(
    final long x,
    final long y)
  {
    this.setAtOffsetAndIndex(this.offset, 0, x);
    this.setAtOffsetAndIndex(this.offset, 1, y);
  }

  @Override public int hashCode()
  {
    final long prime = 31L;
    long result = 1L;
    result = (prime * result) + this.getXL();
    result = (prime * result) + this.getYL();
    result = (prime * result) + this.getZL();
    return (int) result;
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder(128);
    builder.append("[PVectorByteBufferedM3L ");
    builder.append(this.getXL());
    builder.append(" ");
    builder.append(this.getYL());
    builder.append(" ");
    builder.append(this.getZL());
    builder.append("]");
    final String r = builder.toString();
    return NullCheck.notNull(r);
  }

  @Override public boolean equals(
    final @Nullable Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final PVectorByteBufferedM3L<?> other = (PVectorByteBufferedM3L<?>) obj;
    if (this.getXL() != other.getXL()) {
      return false;
    }
    if (this.getYL() != other.getYL()) {
      return false;
    }
    return this.getZL() == other.getZL();
  }

  @Override public void copyFromTyped3L(final PVectorReadable3LType<T> in_v)
  {
    this.setAtOffsetAndIndex(this.offset, 0, in_v.getXL());
    this.setAtOffsetAndIndex(this.offset, 1, in_v.getYL());
    this.setAtOffsetAndIndex(this.offset, 2, in_v.getZL());
  }

  @Override public void copyFromTyped2L(final PVectorReadable2LType<T> in_v)
  {
    this.setAtOffsetAndIndex(this.offset, 0, in_v.getXL());
    this.setAtOffsetAndIndex(this.offset, 1, in_v.getYL());
  }
}