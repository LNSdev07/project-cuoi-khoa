import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOrEditQuanLyKhachHangComponent } from './create-or-edit-quan-ly-khach-hang.component';

describe('CreateOrEditQuanLyKhachHangComponent', () => {
  let component: CreateOrEditQuanLyKhachHangComponent;
  let fixture: ComponentFixture<CreateOrEditQuanLyKhachHangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateOrEditQuanLyKhachHangComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOrEditQuanLyKhachHangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
