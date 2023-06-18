package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.RemoveAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.RemoveAccessibilityTagsResult;

public class RemoveAccessibilityTagsLambda extends LambdaActivityRunner<RemoveAccessibilityTagsRequest, RemoveAccessibilityTagsResult> implements RequestHandler<AuthenticatedLambdaRequest<RemoveAccessibilityTagsRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveAccessibilityTagsRequest> input, Context context) {
         return super.runActivity(
                 () -> {
        System.out.println("NOAH HANDLE REQUEST LAMBDA");
                     RemoveAccessibilityTagsRequest unauthenticatedRequest = input.fromBody(RemoveAccessibilityTagsRequest.class);
                 return RemoveAccessibilityTagsRequest.builder()
                                 .withPlaceId(unauthenticatedRequest.getPlaceId())
                                 .withTagsToRemove(unauthenticatedRequest.getTagsToRemove())
                                 .build();
                 },
                 (request, serviceComponent) -> {
                     try {
                         return serviceComponent.provideRemoveTagsActivity().handleRequest(request);
                     } catch (Exception e){
                         e.printStackTrace();
                     }
                         return serviceComponent.provideRemoveTagsActivity().handleRequest(request);
                 }
         );
    }
}
