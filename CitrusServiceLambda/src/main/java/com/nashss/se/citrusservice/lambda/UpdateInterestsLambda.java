package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.UpdateInterestsRequest;

import com.nashss.se.citrusservice.activity.results.UpdateInterestsResult;

import java.util.Collections;
import java.util.Set;

public class UpdateInterestsLambda extends LambdaActivityRunner<UpdateInterestsRequest, UpdateInterestsResult> implements RequestHandler<AuthenticatedLambdaRequest<UpdateInterestsRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateInterestsRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateInterestsRequest unauthenticatedRequest = input.fromBody(UpdateInterestsRequest.class);
                    return UpdateInterestsRequest.builder()
                            .withUserId(unauthenticatedRequest.getUserId())
                            .withUserInterests(unauthenticatedRequest.getUserInterests())
                            .build();
                },
                (request,serviceComponent) ->
                        serviceComponent.provideUpdateInterestsActivity().handleRequest(request)
        );
    }
}
