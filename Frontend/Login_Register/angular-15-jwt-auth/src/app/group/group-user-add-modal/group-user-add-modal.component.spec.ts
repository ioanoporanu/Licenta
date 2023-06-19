import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupUserAddModalComponent } from './group-user-add-modal.component';

describe('GroupUserAddModalComponent', () => {
  let component: GroupUserAddModalComponent;
  let fixture: ComponentFixture<GroupUserAddModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroupUserAddModalComponent]
    });
    fixture = TestBed.createComponent(GroupUserAddModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
