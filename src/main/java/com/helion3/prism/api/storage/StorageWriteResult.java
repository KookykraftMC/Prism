/*
 * This file is part of Prism, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 Helion3 http://helion3.com/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.helion3.prism.api.storage;

import com.mongodb.bulk.BulkWriteResult;

public class StorageWriteResult implements StorageResult {

    private final int deletedCount;
    private final int insertedCount;
    private final int matchedCount;
    private final int modifiedCount;
    private final boolean isModifiedCountAvailable;
    private final boolean wasAcknowledged;

    public StorageWriteResult(int deletedCount, int insertedCount, int matchedCount, int modifiedCount, boolean isModifiedCountAvailable,
            boolean wasAcknowledged) {
        this.deletedCount = deletedCount;
        this.insertedCount = insertedCount;
        this.matchedCount = matchedCount;
        this.modifiedCount = modifiedCount;
        this.isModifiedCountAvailable = isModifiedCountAvailable;
        this.wasAcknowledged = wasAcknowledged;
    }

    public StorageWriteResult(BulkWriteResult bwr) {
        this.deletedCount = bwr.getDeletedCount();
        this.insertedCount = bwr.getInsertedCount();
        this.matchedCount = bwr.getMatchedCount();
        this.modifiedCount = bwr.getModifiedCount();
        this.isModifiedCountAvailable = bwr.isModifiedCountAvailable();
        this.wasAcknowledged = bwr.wasAcknowledged();
    }

    @Override public int getDeletedCount() {
        return deletedCount;
    }

    @Override public boolean wasAcknowledged() {
        return wasAcknowledged;
    }

    @Override public int getInsertedCount() {
        return insertedCount;
    }

    @Override public int getMatchedCount() {
        return matchedCount;
    }

    @Override public boolean isModifiedCountAvailable() {
        return isModifiedCountAvailable;
    }

    @Override public int getModifiedCount() {
        return modifiedCount;
    }

    @Override public String getResultInfo() {
        String s;
        if (isModifiedCountAvailable) {
            s = "Acknowledged: " + wasAcknowledged +
                    ", Inserted: " + insertedCount +
                    ", Deleted: " + deletedCount +
                    ", Matched: " + matchedCount +
                    ", Modified: " + modifiedCount;
        } else {
            s = "Acknowledged: " + wasAcknowledged +
                    ", Inserted: " + insertedCount +
                    ", Deleted: " + deletedCount +
                    ", Matched: " + matchedCount;
        }
        return s;
    }
}