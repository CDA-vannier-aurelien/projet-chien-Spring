$('.delete-form').submit(function (e) {
  e.preventDefault()
  var currentForm = this
  var nom = currentForm.closest('tr').getElementsByClassName('td-nom')[0]
    .textContent

  var prenom = currentForm.closest('tr').getElementsByClassName('td-prenom')[0]
    .textContent

  bootbox.confirm({
    message: 'Etes-vous sur de vouloir supprimer ' + nom + ' ' + prenom + ' ?',
    buttons: {
      cancel: {
        label: '<i class="fa fa-times"></i> Annuler'
      },
      confirm: {
        label: '<i class="fa fa-check"></i> Supprimer'
      }
    },
    callback: function (result) {
      if (result) {
        currentForm.submit()
      }
    }
  })
})

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

$(document).ready(function () {
  $('#myTable').DataTable({
    //ordering: false
  })
})
