import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CoursesService } from 'src/app/courses/services/courses.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.scss']
})
export class DeleteDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: string,
    private service: CoursesService,
    private _snackBar: MatSnackBar
    ) { }

  ngOnInit(): void {
  }

  onDelete() {
    console.log(this.data);
    this.service.delete(this.data);
  }

}
