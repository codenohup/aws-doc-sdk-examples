//snippet-sourcedescription:[CopyObjectStorage.java demonstrates how to copy an object and set its storage.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-service:[Amazon S3]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.s3;

// snippet-start:[s3.java2.copy_store.main]
// snippet-start:[s3.java2.copy_store.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
// snippet-end:[s3.java2.copy_store.import]

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class CopyObjectStorage {
    public static void main(String[] args) {
        final String usage = """

            Usage:
                <objectKey> <fromBucket> <toBucket>

            Where:
                objectKey - The name of the object (for example, book.pdf).
                fromBucket - The S3 bucket name that contains the object (for example, bucket1).
                toBucket - The S3 bucket to copy the object to (for example, bucket2).
            """;

        if (args.length != 3) {
            System.out.println(usage);
            System.exit(1);
        }

        String objectKey = args[0];
        String fromBucket = args[1];
        String toBucket = args[2];
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        copyBucketObject(s3, fromBucket, objectKey, toBucket);
        s3.close();
    }

    public static void copyBucketObject(S3Client s3, String fromBucket, String objectKey, String toBucket) {
        CopyObjectRequest copyReq = CopyObjectRequest.builder()
            .sourceBucket(fromBucket)
            .sourceKey(objectKey)
            .storageClass("DEEP_ARCHIVE")
            .destinationBucket(toBucket)
            .destinationKey(objectKey)
            .build();

        try {
            s3.copyObject(copyReq);
            System.out.println("You have successfully stored " + objectKey + " to DEEP_ARCHIVE.");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
// snippet-end:[s3.java2.copy_store.main]
