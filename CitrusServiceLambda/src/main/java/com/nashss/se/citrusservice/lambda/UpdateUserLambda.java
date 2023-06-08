package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.UpdateUserRequest;
import com.nashss.se.citrusservice.activity.results.UpdateUserResult;

public class UpdateUserLambda extends LambdaActivityRunner<UpdateUserRequest, UpdateUserResult> implements RequestHandler<AuthenticatedLambdaRequest<UpdateUserRequest>,LambdaResponse> {


    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateUserRequest unAuthenticatedRequest = input.fromBody(UpdateUserRequest.class);

                    String userIdFromPath = input.getPathParameters().get("userId");

                    return UpdateUserRequest.builder()
                            .withUserId(userIdFromPath)

                            .withFirstName(unAuthenticatedRequest.getFirstName())
                            .withLastName(unAuthenticatedRequest.getLastName())
                            .withGender(unAuthenticatedRequest.getGender())
                            .withDateOfBirth(unAuthenticatedRequest.getDateOfBirth())
                            .withUserInterests(unAuthenticatedRequest.getUserInterests())
                            .build();

                },
                (request, serviceComponent) ->

                    serviceComponent.provideUpdateUserActivity().handleRequest(request)
        );
    }
}
