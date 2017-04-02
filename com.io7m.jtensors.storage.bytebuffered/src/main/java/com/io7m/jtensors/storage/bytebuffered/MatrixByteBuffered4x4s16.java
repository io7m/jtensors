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

package com.io7m.jtensors.storage.bytebuffered;

import com.io7m.ieee754b16.Binary16;
import com.io7m.jnull.NullCheck;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix4x4D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix4x4F;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * <p>A storage matrix.</p>
 * <p>Storage component type: {@code binary16}</p>
 * <p>Storage component count: {@code 4x4}</p>
 */

public final class MatrixByteBuffered4x4s16 implements MatrixByteBuffered4x4Type
{
  private final CharBuffer view;
  private final ByteBuffer buffer;

  private MatrixByteBuffered4x4s16(
    final ByteBuffer bb)
  {
    this.buffer = NullCheck.notNull(bb, "Buffer");
    this.view = this.buffer.asCharBuffer();
  }

  /**
   * @return A heap-backed matrix in native byte order
   */

  public static MatrixByteBuffered4x4Type createHeap()
  {
    return createWith(ByteBuffer.allocate((4 * 4) * 2).order(ByteOrder.nativeOrder()));
  }

  /**
   * @return A direct-memory matrix in native byte order
   */

  public static MatrixByteBuffered4x4Type createDirect()
  {
    return createWith(ByteBuffer.allocateDirect((4 * 4) * 2).order(ByteOrder.nativeOrder()));
  }

  /**
   * @param b A byte buffer
   *
   * @return A matrix backed by the given byte buffer
   */

  public static MatrixByteBuffered4x4Type createWith(
    final ByteBuffer b)
  {
    return new MatrixByteBuffered4x4s16(b);
  }

  private static int indexUnsafe(
    final int row,
    final int column)
  {
    return (column * 4) + row;
  }

  @Override
  public double r0c0()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(0, 0)));
  }

  @Override
  public double r0c1()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(0, 1)));
  }

  @Override
  public double r0c2()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(0, 2)));
  }

  @Override
  public double r0c3()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(0, 3)));
  }

  @Override
  public double r1c0()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(1, 0)));
  }

  @Override
  public double r1c1()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(1, 1)));
  }

  @Override
  public double r1c2()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(1, 2)));
  }

  @Override
  public double r1c3()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(1, 3)));
  }

  @Override
  public double r2c0()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(2, 0)));
  }

  @Override
  public double r2c1()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(2, 1)));
  }

  @Override
  public double r2c2()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(2, 2)));
  }

  @Override
  public double r2c3()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(2, 3)));
  }

  @Override
  public double r3c0()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(3, 0)));
  }

  @Override
  public double r3c1()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(3, 1)));
  }

  @Override
  public double r3c2()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(3, 2)));
  }

  @Override
  public double r3c3()
  {
    return Binary16.unpackDouble(this.view.get(indexUnsafe(3, 3)));
  }

  @Override
  public void setMatrix4x4D(
    final Matrix4x4D m)
  {
    this.view.put(indexUnsafe(0, 0), Binary16.packDouble(m.r0c0()));
    this.view.put(indexUnsafe(0, 1), Binary16.packDouble(m.r0c1()));
    this.view.put(indexUnsafe(0, 2), Binary16.packDouble(m.r0c2()));
    this.view.put(indexUnsafe(0, 3), Binary16.packDouble(m.r0c3()));

    this.view.put(indexUnsafe(1, 0), Binary16.packDouble(m.r1c0()));
    this.view.put(indexUnsafe(1, 1), Binary16.packDouble(m.r1c1()));
    this.view.put(indexUnsafe(1, 2), Binary16.packDouble(m.r1c2()));
    this.view.put(indexUnsafe(1, 3), Binary16.packDouble(m.r1c3()));

    this.view.put(indexUnsafe(2, 0), Binary16.packDouble(m.r2c0()));
    this.view.put(indexUnsafe(2, 1), Binary16.packDouble(m.r2c1()));
    this.view.put(indexUnsafe(2, 2), Binary16.packDouble(m.r2c2()));
    this.view.put(indexUnsafe(2, 3), Binary16.packDouble(m.r2c3()));

    this.view.put(indexUnsafe(3, 0), Binary16.packDouble(m.r3c0()));
    this.view.put(indexUnsafe(3, 1), Binary16.packDouble(m.r3c1()));
    this.view.put(indexUnsafe(3, 2), Binary16.packDouble(m.r3c2()));
    this.view.put(indexUnsafe(3, 3), Binary16.packDouble(m.r3c3()));
  }

  @Override
  public void setMatrix4x4F(
    final Matrix4x4F m)
  {
    this.view.put(indexUnsafe(0, 0), Binary16.packDouble(m.r0c0()));
    this.view.put(indexUnsafe(0, 1), Binary16.packDouble(m.r0c1()));
    this.view.put(indexUnsafe(0, 2), Binary16.packDouble(m.r0c2()));
    this.view.put(indexUnsafe(0, 3), Binary16.packDouble(m.r0c3()));

    this.view.put(indexUnsafe(1, 0), Binary16.packDouble(m.r1c0()));
    this.view.put(indexUnsafe(1, 1), Binary16.packDouble(m.r1c1()));
    this.view.put(indexUnsafe(1, 2), Binary16.packDouble(m.r1c2()));
    this.view.put(indexUnsafe(1, 3), Binary16.packDouble(m.r1c3()));

    this.view.put(indexUnsafe(2, 0), Binary16.packDouble(m.r2c0()));
    this.view.put(indexUnsafe(2, 1), Binary16.packDouble(m.r2c1()));
    this.view.put(indexUnsafe(2, 2), Binary16.packDouble(m.r2c2()));
    this.view.put(indexUnsafe(2, 3), Binary16.packDouble(m.r2c3()));

    this.view.put(indexUnsafe(3, 0), Binary16.packDouble(m.r3c0()));
    this.view.put(indexUnsafe(3, 1), Binary16.packDouble(m.r3c1()));
    this.view.put(indexUnsafe(3, 2), Binary16.packDouble(m.r3c2()));
    this.view.put(indexUnsafe(3, 3), Binary16.packDouble(m.r3c3()));
  }

  @Override
  public ByteBuffer byteBuffer()
  {
    return this.buffer;
  }
}
