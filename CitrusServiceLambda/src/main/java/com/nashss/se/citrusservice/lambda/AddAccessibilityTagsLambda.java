package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.AddAccessibilityTagsResult;

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
        return super.runActivity(
                () -> {
                    AddAccessibilityTagsRequest unauthenticatedRequest = input.fromBody(AddAccessibilityTagsRequest.class);
                    return input.fromPath(claims ->
                            AddAccessibilityTagsRequest.builder()
                                    .withPlaceId(unauthenticatedRequest.getPlaceId())
                                    .withAccessibilityTags(unauthenticatedRequest.getAccessibilityTags())
                                    .build());
                },
                (request,serviceComponent) -> {
                    try {
                        return serviceComponent.provideAddAccessibilityTagsActivity().handleRequest(request);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                        return serviceComponent.provideAddAccessibilityTagsActivity().handleRequest(request);
                }
        );
    }
}
