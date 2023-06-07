package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.GetUserRequest;
import com.nashss.se.citrusservice.activity.results.GetUserResult;
import com.amazonaws.services.lambda.runtime.Context;

public class GetUserLambda extends LambdaActivityRunner<GetUserRequest, GetUserResult> implements RequestHandler<AuthenticatedLambdaRequest<GetUserRequest>, LambdaResponse> {


    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetUserRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetUserRequest.builder()
                                .withUserId(path.get("userId"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideGetUserActivity().handleRequest(request));
    }
}