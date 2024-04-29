import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;

  constructor(private userService: UserService) { }
  
  ngOnInit(): void {
    this.userService.getMyInfo().subscribe({
      next: userData => {
        this.currentUser = userData;
        },
      error: err  => {
        console.error('Error fetching user profile:', err);
      }
    });
  }
}
