//snippet-sourcedescription:[RevokeGrant.java demonstrates how to revoke a grant for the specified customer master key (CMK).]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-service:[AWS Key Management Service]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.kms;

// snippet-start:[kms.java2_revoke_grant.main]
// snippet-start:[kms.java2_revoke_grant.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.KmsException;
import software.amazon.awssdk.services.kms.model.RevokeGrantRequest;
// snippet-end:[kms.java2_revoke_grant.import]

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class RevokeGrant {
    public static void main(String[] args) {
        final String usage = """

            Usage:
                <keyId> <grantId>\s

            Where:
                keyId - A unique identifier for the customer master key associated with the grant (for example, xxxxxbcd-12ab-34cd-56ef-1234567890ab).\s
                grantId - A grant id value of the grant revoke.\s
            """;

        if (args.length != 2) {
            System.out.println(usage);
            System.exit(1);
        }

        String keyId = args[0];
        String grantId = args[1];
        Region region = Region.US_WEST_2;
        KmsClient kmsClient = KmsClient.builder()
            .region(region)
            .build();

        revokeKeyGrant(kmsClient, keyId, grantId);
        kmsClient.close();
    }

    public static void revokeKeyGrant(KmsClient kmsClient, String keyId, String grantId) {
        try {
            RevokeGrantRequest grantRequest = RevokeGrantRequest.builder()
                .keyId(keyId)
                .grantId(grantId)
                .build();

            kmsClient.revokeGrant(grantRequest);

        } catch (KmsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
 // snippet-end:[kms.java2_revoke_grant.main]
