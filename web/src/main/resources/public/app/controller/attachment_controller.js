/**
 * Created by nataliia on 15.07.16.
 */

App.controller('AttachmentController', ['$scope', 'AttachmentService',

    function ($scope, AttachmentService) {
        var attachmentController = this;

        attachmentController.attachment = {attachmentId: null, filePath: ''};
        attachmentController.attachments = [];

        attachmentController.getAllAttachments = function () {
            AttachmentService.getAllAttachments()
                .then(
                    function (data) {
                        attachmentController.attachments = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching attachments');
                    }
                );
        };

        attachmentController.createAttachment = function (attachment) {
            if(attachment.date == null || attachment.date.length == 0){
                console.log('specifying date');
                attachment.date = new Date().toJSON().slice(0,10);
                console.log(attachment.date);
            }
            AttachmentService.createAttachment(attachment)
                .then(
                    attachmentController.getAllAttachments,
                    function (errResponse) {
                        console.error('error while creating attachment');
                    }
                );
        };

        attachmentController.updateAttachment = function (attachment, attachmentId) {
            AttachmentService.updateAttachment(attachment, attachmentId)
                .then(
                    attachmentController.getAllAttachments,
                    function (errResponse) {
                        console.error('error while updating attachment');
                    }
                );
        };

        attachmentController.deleteAttachment = function (attachmentId) {
            AttachmentService.deleteAttachment(attachmentId)
                .then(
                    attachmentController.getAllAttachments,
                    function (errResponse) {
                        console.error('error while deleting attachment');
                    }
                );
        };

        attachmentController.deleteAllAttachments = function () {
            AttachmentService.deleteAllAttachments()
                .then(
                    attachmentController.getAllAttachments,
                    function (errResponse) {
                        console.error('error while deleting attachments');
                    }
                );
        };

        attachmentController.getAllAttachments();

        attachmentController.submit = function () {
            if (attachmentController.attachment.attachmentId === null) {
                console.log('saving new attachment', attachmentController.attachment);
                attachmentController.createAttachment(attachmentController.attachment);
            } else {
                var attachmentId = parseInt(attachmentController.attachment.attachmentId);
                console.log('updating attachment', attachmentController.attachment);
                attachmentController.updateAttachment(attachmentController.attachment, attachmentId);
            }
            attachmentController.reset();
        };

        attachmentController.edit = function (attachmentId) {
            console.log('id to be edited: ' + attachmentId);
            for (var i = 0; i < attachmentController.attachments.length; i++) {
                if (attachmentController.attachments[i].attachmentId === attachmentId) {
                    attachmentController.attachment = angular.copy(attachmentController.attachments[i]);
                    break;
                }
            }
        };

        attachmentController.remove = function (attachmentId) {
            if (attachmentController.attachment.attachmentId === attachmentId) {
                attachmentController.reset();
            }
            attachmentController.deleteAttachment(attachmentId);
        };

        attachmentController.reset = function () {
            attachmentController.attachment = {attachmentId: null, name: '', description: '', date: '', filePath: ''};
            $scope.attachmentForm.$setPristine();
        }
    }
]);