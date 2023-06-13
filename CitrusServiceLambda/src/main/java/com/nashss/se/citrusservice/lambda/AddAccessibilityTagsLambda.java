package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.AddAccessibilityTagsResult;

import java.util.Collections;

public class AddAccessibilityTagsLambda extends LambdaActivityRunner<AddAccessibilityTagsRequest, AddAccessibilityTagsResult> implements RequestHandler<AuthenticatedLambdaRequest<AddAccessibilityTagsRequest>,LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddAccessibilityTagsRequest> input, Context context) {
        System.out.println("NOAH line 21");
        return super.runActivity(
                () -> {
                    System.out.println("NOAH line 24");
                    AddAccessibilityTagsRequest unauthenticatedRequest = input.fromBody(AddAccessibilityTagsRequest.class);
                    System.out.println("NOAH line 26" + unauthenticatedRequest.toString());

                    return input.fromUserClaims(claims ->
                            AddAccessibilityTagsRequest.builder()
                                    .withPlaceId(claims.get("placeId"))
                                    .withAccessibilityTagsToAdd(unauthenticatedRequest.getAccessibilityTagsToAdd())
                                    .build());
                },
                (request,serviceComponent) ->
                        serviceComponent.provideAddAccessibilityTagsActivity().handleRequest(request)
        );
    }
}
