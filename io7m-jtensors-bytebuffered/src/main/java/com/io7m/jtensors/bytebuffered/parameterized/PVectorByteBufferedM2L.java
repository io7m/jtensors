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
import com.io7m.jtensors.parameterized.PVector2LType;
import com.io7m.jtensors.parameterized.PVectorReadable2LType;

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

public final class PVectorByteBufferedM2L<T> implements PVector2LType<T>
{
  private final ByteBuffer buffer;
  private final long       offset;

  private PVectorByteBufferedM2L(
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

  public static <T> PVector2LType<T> newVectorFromByteBuffer(
    final ByteBuffer b,
    final long byte_offset)
  {
    return new PVectorByteBufferedM2L<T>(b, byte_offset);
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
    this.buffer.putLong(PVectorByteBufferedM2L.getByteOffsetForIndex(o, i), x);
  }

  private long getAtOffsetAndIndex(
    final long o,
    final int i)
  {
    return this.buffer.getLong(
      PVectorByteBufferedM2L.getByteOffsetForIndex(o, i));
  }

  @Override public long getYL()
  {
    return this.getAtOffsetAndIndex(this.offset, 1);
  }

  @Override public void setYL(final long y)
  {
    this.setAtOffsetAndIndex(this.offset, 1, y);
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
    return (int) result;
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder(128);
    builder.append("[PVectorByteBufferedM2L ");
    builder.append(this.getXL());
    builder.append(" ");
    builder.append(this.getYL());
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
    final PVectorByteBufferedM2L<?> other = (PVectorByteBufferedM2L<?>) obj;
    if (this.getXL() != other.getXL()) {
      return false;
    }
    return this.getYL() == other.getYL();
  }

  @Override public void copyFromTyped2L(final PVectorReadable2LType<T> in_v)
  {
    this.setAtOffsetAndIndex(this.offset, 0, in_v.getXL());
    this.setAtOffsetAndIndex(this.offset, 1, in_v.getYL());
  }
}