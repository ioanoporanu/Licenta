import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupDisplayModalComponent } from './group-display-modal.component';

describe('GroupDisplayModalComponent', () => {
  let component: GroupDisplayModalComponent;
  let fixture: ComponentFixture<GroupDisplayModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroupDisplayModalComponent]
    });
    fixture = TestBed.createComponent(GroupDisplayModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
