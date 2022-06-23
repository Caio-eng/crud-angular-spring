import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

import { CoursesService } from './../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute
    ) {
    this.form = this.formBuilder.group({
      name: [null],
      category: [null]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.save(this.form.value)
    .subscribe(result => console.log(result), error => this.onError());
  }

  onCancel() {
    this.router.navigate([''], {relativeTo: this.route});
  }

  private onError() {
    this._snackBar.open('Erro ao salvar curso.', '',  {duration: 5000 });
  }

}
