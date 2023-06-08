package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.CreateUserRequest;
import com.nashss.se.citrusservice.activity.results.CreateUserResult;

public class CreateUserLambda extends LambdaActivityRunner<CreateUserRequest, CreateUserResult> implements RequestHandler<LambdaRequest<CreateUserRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateUserRequest> input, Context context) {
        System.out.println(input.toString());
        return super.runActivity(
                () -> {

                    CreateUserRequest unauthenticatedRequest = input.fromBody(CreateUserRequest.class);
                    return input.fromPath(claims ->
                            CreateUserRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withFirstName(unauthenticatedRequest.getFirstName())
                                    .withLastName(unauthenticatedRequest.getLastName())
                                    .withGender(unauthenticatedRequest.getGender())
                                    .withDateOfBirth(unauthenticatedRequest.getDateOfBirth())
                                    .withUserInterests(unauthenticatedRequest.getUserInterests())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserActivity().handleRequest(request)
        );
    }
}
