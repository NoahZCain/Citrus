package com.nashss.se.Citrus.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.nashss.se.Citrus.activity.requests.GetUserRequest;
import com.nashss.se.Citrus.activity.results.GetUserResult;

public class GetUserLambda extends LambdaActivityRunner<GetUserRequest, GetUserResult> implements RequestHandler<LambdaRequest<GetUserRequest>, LambdaResponse> {


    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetUserRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetUserRequest.builder()
                                .withUserId(path.get("id"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideGetUserActivity().handleRequest(request));
    }
}