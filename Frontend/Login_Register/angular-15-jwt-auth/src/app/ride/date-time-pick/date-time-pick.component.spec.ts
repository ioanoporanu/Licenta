import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DateTimePickComponent } from './date-time-pick.component';

describe('DateTimePickComponent', () => {
  let component: DateTimePickComponent;
  let fixture: ComponentFixture<DateTimePickComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DateTimePickComponent]
    });
    fixture = TestBed.createComponent(DateTimePickComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
